package ru.badin.springbootf1webservice.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.DTO.TeamDto;
import ru.badin.springbootf1webservice.Services.TeamServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamServiceImpl teamService;

    public TeamController(TeamServiceImpl teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/hal")
    public ResponseEntity<Map<String, Object>> getAllTeamsWithHAL(@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int count) {
        return ResponseEntity.ok(teamService.getAllTeamsHal(index, count));
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamDto> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {
        teamService.createTeam(teamDto);
        return new ResponseEntity<>(teamDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable Long id) {
        TeamDto teamDto = teamService.getTeamById(id);
        if (teamDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamDto);
    }

    @PutMapping("/{id}")
    public void updateTeam(@PathVariable Long id, @RequestBody TeamDto teamDto) {
        teamService.updateTeam(id, teamDto);
    }


    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}