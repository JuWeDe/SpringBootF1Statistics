package ru.badin.springbootf1webservice.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.HAL.HAL;
import ru.badin.springbootf1webservice.Services.CarService;
import ru.badin.springbootf1webservice.Services.RacerService;
import ru.badin.springbootf1webservice.Services.TeamService;
import ru.badin.springbootf1webservice.model.Team;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;
    private final RacerService racerService;
    private final CarService carService;

    @Autowired
    public TeamController(TeamService teamService, RacerService racerService, CarService carService) {
        this.teamService = teamService;
        this.racerService = racerService;
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team savedTeam = teamService.createTeam(team);

        return ResponseEntity.created(URI.create("/api/teams/" + savedTeam.getId())).body(savedTeam);
    }


    @PostMapping("/{teamId}/addCarToTeam/{carId}")
    public ResponseEntity<String> addCarToTeam(@PathVariable Long teamId, @PathVariable Long carId) {
        teamService.addCarToTeam(teamId, carId);
        return ResponseEntity.ok("Car added to team successfully");
    }

    @PostMapping("/{teamId}/addRacer/{racerId}")
    public ResponseEntity<String> addRacerToTeam(@PathVariable Long teamId, @PathVariable Long racerId) {
        teamService.addRacerToTeam(teamId, racerId);
        return ResponseEntity.ok("Racer added to team successfully");
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        Team team = teamService.getTeamById(id);
        if (team != null) {
            team.setName(updatedTeam.getName());
            team.setPoints(updatedTeam.getPoints());
            team.setTeamPrinciple(updatedTeam.getTeamPrinciple());
            return teamService.updateTeam(id, team);
        }
        return null;
    }

    @GetMapping("/hal")
    public ResponseEntity<Map<String, Object>> getAllTeamsWithHAL(@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int count) {
        Map<String, Object> responseBody = teamService.getAllTeamsWithHAL(index, count);

        responseBody.put("create", HAL.addHypermediaLink("/teams", "create", "POST", "Create a new team"));

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);

        if (team == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", team.getId());
        responseBody.put("name", team.getName());
        responseBody.put("points", team.getPoints());
        responseBody.put("teamPrinciple", team.getTeamPrinciple());

        responseBody.put("update", HAL.addHypermediaLink("/api/teams/" + team.getId(), "update", "PUT", "Update the team"));
        responseBody.put("delete", HAL.addHypermediaLink("/api/teams/" + team.getId(), "delete", "DELETE", "Delete the team"));

        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}