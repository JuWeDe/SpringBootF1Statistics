package ru.badin.springbootf1webservice.Services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.DTO.RacerDto;
import ru.badin.springbootf1webservice.HAL.HAL;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.repostory.RacerRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RacerServiceImpl implements RacerService {

    private final RacerRepository racerRepository;
    private final ModelMapper modelMapper;

    public RacerServiceImpl(RacerRepository racerRepository, ModelMapper modelMapper) {
        this.racerRepository = racerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RacerDto getRacerById(Long id) {
        Optional<Racer> racerOptional = racerRepository.findById(id);
        return racerOptional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public List<RacerDto> getAllRacers() {
        List<Racer> racers = racerRepository.findAll();
        return racers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void createRacer(RacerDto racerDto) {
        Racer racer = mapToEntity(racerDto);
        racerRepository.save(racer);
    }

    @Override
    public void updateRacer(Long id, RacerDto racerDto) {
        Optional<Racer> racerOptional = racerRepository.findById(id);
        if (racerOptional.isPresent()) {
            Racer racer = mapToEntity(racerDto);
            racer.setId(id);
            racerRepository.save(racer);
        } else {
            throw new RuntimeException("Racer not found: " + id);
        }
    }

    @Override
    public void deleteRacer(Long id) {
        racerRepository.deleteById(id);

    }


    private RacerDto mapToDTO(Racer racer) {
        return modelMapper.map(racer, RacerDto.class);
    }

    private Racer mapToEntity(RacerDto racerDto) {
        return modelMapper.map(racerDto, Racer.class);
    }

    @Override
    public Map<String, Object> getAllRacersHal(int index, int count) {
        Page<RacerDto> racerPage = getAllRacers(index, count);
        String baseUrl = "/api/racers";
        int pageNumber = racerPage.getNumber();
        int pageSize = racerPage.getSize();
        int total = racerPage.getTotalPages();

        List<Map<String, Object>> embeddedRacers = racerPage.getContent().stream()
                .map(this::createRacerResource)
                .toList();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("_embedded", Collections.singletonMap("racers", embeddedRacers));
        response.put("_links", HAL.paginateAsDictionary(baseUrl, pageNumber, pageSize, total));

        return response;
    }

    private Map<String, Object> createRacerResource(RacerDto racerDto) {
        Map<String, Object> racerResource = new LinkedHashMap<>();
        racerResource.put("_links", createRacerLinks(racerDto));
        racerResource.put("id", racerDto.getId());
        racerResource.put("name", racerDto.getName());
        racerResource.put("dateOfBirth", racerDto.getDateOfBirth());
        racerResource.put("wins", racerDto.getWins());
        racerResource.put("championships", racerDto.getChampionships());
        racerResource.put("points", racerDto.getPoints());

        return racerResource;
    }

    private Map<String, Object> createRacerLinks(RacerDto racerDto) {
        String baseUrl = "/api/racers";
        Long racerId = racerDto.getId();
        Map<String, Object> links = new LinkedHashMap<>();
        links.put("self", Collections.singletonMap("href", baseUrl + "/" + racerId));
        links.put("car", Collections.singletonMap("href", "api/cars/" + racerDto.getCarId()));
        links.put("team", Collections.singletonMap("href", "api/teams/" + racerDto.getTeamId()));
        return links;
    }

    public Page<RacerDto> getAllRacers(int index, int count) {
        PageRequest pageable = PageRequest.of(index, count);
        Page<Racer> racerPage = racerRepository.findAll(pageable);
        List<RacerDto> racerDtos = racerPage.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(racerDtos, pageable, racerPage.getTotalElements());
    }

}
