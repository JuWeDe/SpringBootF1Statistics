package ru.badin.springbootf1webservice.Services;


import com.vaadin.flow.router.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.*;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.HAL.HAL;
import ru.badin.springbootf1webservice.model.Car;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.model.Team;
import java.util.stream.Collectors;
import ru.badin.springbootf1webservice.repostory.CarRepository;
import ru.badin.springbootf1webservice.repostory.RacerRepository;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RacerService {
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;
    private final TeamRepository teamRepository;

    public RacerService(RacerRepository racerRepository, CarRepository carRepository, TeamRepository teamRepository) {
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.teamRepository = teamRepository;
    }
    public Page<Racer> getAllRacers(int index, int count) {
        PageRequest pageable = PageRequest.of(index, count);
        return racerRepository.findAll(pageable);
    }

    public Map<String, Object> getAllRacersWithHAL(int index, int count) {
        Page<Racer> racerPage = getAllRacers(index, count);
        String baseUrl = "/api/racers";
        int pageNumber = racerPage.getNumber();
        int pageSize = racerPage.getSize();
        int total = racerPage.getTotalPages();

        List<Map<String, Object>> embeddedRacers = racerPage.getContent().stream()
                .map(this::createRacerResource)
                .collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("_embedded", Collections.singletonMap("racers", embeddedRacers));
        response.put("_links", HAL.paginateAsDictionary(baseUrl, pageNumber, pageSize, total));

        return response;
    }

    private Map<String, Object> createRacerResource(Racer racer) {
        Map<String, Object> racerResource = new LinkedHashMap<>();
        racerResource.put("_links", createRacerLinks(racer));
        racerResource.put("id", racer.getId());
        racerResource.put("name", racer.getName());
        racerResource.put("dateOfBirth", racer.getDateOfBirth());
        racerResource.put("wins", racer.getWins());
        racerResource.put("championships", racer.getChampionships());
        racerResource.put("points", racer.getPoints());
        return racerResource;
    }

    private Map<String, Object> createRacerLinks(Racer racer) {
        String baseUrl = "/api/racers";
        Long racerId = racer.getId();
        Map<String, Object> links = new LinkedHashMap<>();
        links.put("self", Collections.singletonMap("href", baseUrl + "/" + racerId));
        links.put("team", Collections.singletonMap("href", baseUrl + "/" + racerId + "/team"));
        links.put("car", Collections.singletonMap("href", baseUrl + "/" + racerId + "/car"));
        return links;
    }

    @Transactional
    public void addCarToRacer(Long racerId, Long carId) {
        Racer racer = racerRepository.findById(racerId).orElseThrow(() -> new NotFoundException("Racer not found with id: " + racerId));

        Car car = carRepository.findById(carId).orElseThrow(() -> new NotFoundException("Car not found with id: " + carId));

        racer.setCar(car);
        racerRepository.save(racer);
    }

    public Racer updateRacer(Long id, Racer updateRacer) {
        Racer racer = getRacerById(id);
        if (racer != null) {
            racer.setName(updateRacer.getName());
            racer.setChampionships(updateRacer.getChampionships());
            racer.setPoints(updateRacer.getPoints());
            racer.setDateOfBirth(updateRacer.getDateOfBirth());
            racer.setWins(updateRacer.getWins());
            return racerRepository.save(racer);
        }
        return null;
    }


//    public Page<Racer> getAllRacers(int index, int count) {
//        PageRequest pageable = PageRequest.of(index, count);
//        return racerRepository.findAll(pageable);
//    }
//
//    public Map<String, Object> getAllRacersWithHAL(int index, int count) {
//        Page<Racer> racerPage = getAllRacers(index, count);
//        String baseUrl = "/api/racers";
//        int pageNumber = racerPage.getNumber();
//        int pageSize = racerPage.getSize();
//        int total = racerPage.getTotalPages();
//
//        Map<String, Object> response = new LinkedHashMap<>();
//        response.put("_embedded", Collections.singletonMap("racers", racerPage.getContent()));
//        response.put("_links", HAL.paginateAsDictionary(baseUrl, pageNumber, pageSize, total));
//
//        return response;
//    }

    public Racer createRacer(Racer racer) {
        return racerRepository.save(racer);
    }

    public Racer getRacerById(Long id) {
        return racerRepository.findById(id).orElse(null);
    }

    public List<Racer> getAllRacers() {
        return racerRepository.findAll();
    }


    @Transactional
    public void deleteRacer(Long racerId) {
        Racer racer = racerRepository.findById(racerId).orElseThrow(() -> new NotFoundException("Racer not found with id: " + racerId));

        if (racer.getCar() != null) {
            Car car = racer.getCar();
            car.setTeam(null);
            racer.setCar(null);
            carRepository.save(car);
        }

        if (racer.getTeam() != null) {
            Team team = racer.getTeam();
            team.getRacers().remove(racer);
            racer.setTeam(null);
            teamRepository.save(team);
        }

        racerRepository.delete(racer);
    }

}