package ru.badin.springbootf1webservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.model.Team;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import java.util.Map;

@RestController()
public class TeamController {

    private final TeamRepository tr;

    public TeamController(TeamRepository tr) {
        this.tr = tr;
    }
    @GetMapping("/teams")
    Iterable<Team> getTeams() {
        return tr.findAll();
    }

    @DeleteMapping("/team_to_delete/{id}")
    void deleteTeam(@PathVariable Long id) {
        tr.deleteById(id);
    }

        @PutMapping("/team_to_update/{id}")
    public Team updateTeam(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
        Team team = tr.findById(id).get();
        team.setName(body.get("name"));
        team.setPoints(Double.valueOf(body.get("points")));
        team.setPointsInSeason(Double.valueOf(body.get("pointsInSeason")));
        team.setCarId(Long.valueOf(body.get("carId")));
        team.setTeamPrinciple(body.get("teamPrinciple"));
        tr.save(team);
        return team;
    }
    @PostMapping("/team_to_add")
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

        return tr.save(team);

    }



}
