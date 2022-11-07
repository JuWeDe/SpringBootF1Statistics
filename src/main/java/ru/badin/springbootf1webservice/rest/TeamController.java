package ru.badin.springbootf1webservice.rest;


import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.model.Team;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import java.util.Collections;
import java.util.Map;

@RestController()
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository tr) {
        this.teamRepository = tr;
    }

    @GetMapping("/team")
    Iterable<Team> getTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("team/{id}")
    Iterable<Team> getTeamById(@PathVariable Long id) {
        return teamRepository.findAllById(Collections.singleton(id));
    }


    @DeleteMapping("/team/{id}")
    void deleteTeam(@PathVariable Long id) {
        teamRepository.deleteById(id);
    }

    @PutMapping("/team/{id}")
    public Team updateTeam(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
        Team team = teamRepository.findById(id).get();
        if (teamRepository.findById(id).isPresent()) {
            if (body.get("name") != null) {
                team.setName(body.get("name"));
            }
            if (body.get("points") != null) {
                team.setName(body.get("pints"));
            }
            if (body.get("pointsInSeason") != null) {

                team.setPointsInSeason(Double.valueOf(body.get("pointsInSeason")));
            }
            if (body.get("carId") != null) {

                team.setCarId(Long.valueOf(body.get("carId")));
            }
            if (body.get("teamPrinciple") != null) {

                team.setTeamPrinciple(body.get("teamPrinciple"));
            }

        }


        teamRepository.save(team);
        return team;
    }

    @PostMapping("/team")
    public Team create(@RequestBody Map<String, String> body) {
        Team team = new Team();
        String name = body.get("name");
        Double points = Double.valueOf(body.get("points"));
        Double pointsInSeason = Double.valueOf(body.get("pointsInSeason"));
        Long carId = Long.valueOf(body.get("carId"));
        String teamPrinciple = body.get("teamPrinciple");

        team.setName(name);
        team.setPoints(points);
        team.setPointsInSeason(pointsInSeason);
        team.setCarId(carId);
        team.setTeamPrinciple(teamPrinciple);

        return teamRepository.save(team);

    }


}


