package ru.badin.springbootf1webservice.Services;

import com.vaadin.flow.router.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.HAL.HAL;
import ru.badin.springbootf1webservice.model.Car;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.model.Team;
import ru.badin.springbootf1webservice.repostory.CarRepository;
import ru.badin.springbootf1webservice.repostory.RacerRepository;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;

    public TeamService(TeamRepository teamRepository, RacerRepository racerRepository, CarRepository carRepository) {
        this.teamRepository = teamRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
    }

    public Page<Team> getAllTeams(int index, int count) {
        PageRequest pageable = PageRequest.of(index, count);
        return teamRepository.findAll(pageable);
    }


    public Map<String, Object> getAllTeamsWithHAL(int index, int count) {
        Page<Team> teamPage = getAllTeams(index, count);
        String baseUrl = "/teams";
        int pageNumber = teamPage.getNumber();
        int pageSize = teamPage.getSize();
        int total = teamPage.getTotalPages();

        List<Map<String, Object>> embeddedTeams = teamPage.getContent().stream()
                .map(this::createTeamResource)
                .collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("_embedded", Collections.singletonMap("teams", embeddedTeams));
        response.put("_links", HAL.paginateAsDictionary(baseUrl, pageNumber, pageSize, total));

        return response;
    }

    private Map<String, Object> createTeamResource(Team team) {
        Map<String, Object> teamResource = new LinkedHashMap<>();
        teamResource.put("_links", createTeamLinks(team));
        teamResource.put("id", team.getId());
        teamResource.put("name", team.getName());
        teamResource.put("points", team.getPoints());
        teamResource.put("teamPrinciple", team.getTeamPrinciple());
        return teamResource;
    }

    private Map<String, Object> createTeamLinks(Team team) {
        String baseUrl = "/teams";
        Long teamId = team.getId();
        Map<String, Object> links = new LinkedHashMap<>();
        links.put("self", Collections.singletonMap("href", baseUrl + "/" + teamId));
        links.put("racers", Collections.singletonMap("href", "/racers" + "/" + teamId));
        return links;
    }

    @Transactional
    public void addRacerToTeam(Long teamId, Long racerId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundException("Team not found with id: " + teamId));

        Racer racer = racerRepository.findById(racerId)
                .orElseThrow(() -> new NotFoundException("Racer not found with id: " + racerId));

        racer.setTeam(team);
        racerRepository.save(racer);
    }

    @Transactional
    public void addCarToTeam(Long teamId, Long carId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundException("Team not found with id: " + teamId));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car not found with id: " + carId));

        car.setTeam(team);
        carRepository.save(car);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team updatedTeam) {
        Team team = getTeamById(id);
        if (team != null) {
            team.setName(updatedTeam.getName());
            team.setPoints(updatedTeam.getPoints());
            team.setTeamPrinciple(updatedTeam.getTeamPrinciple());
            return teamRepository.save(team);
        }
        return null;
    }

    public void deleteTeam(Long id) {
        Team team = getTeamById(id);
        if (team != null) {
            teamRepository.delete(team);
        }
    }
}