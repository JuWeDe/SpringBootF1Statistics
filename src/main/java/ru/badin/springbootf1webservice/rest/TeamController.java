package ru.badin.springbootf1webservice.rest;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.Services.TeamService;
import ru.badin.springbootf1webservice.model.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/hal")
    public ResponseEntity<Map<String, Object>> getTeams (@RequestParam(defaultValue = "0") int index,
                                                       @RequestParam(defaultValue = "25") int count) {
        Page<Team> teams = teamService.getTeams(index, count);

        List<Team> teamList = teams.getContent();
        Map<String, Object> response = new HashMap<>();
        int total = teams.getTotalPages();
        response.put("items", teamList);
        response.put("count", teamList.size());
        response.put("total", teams.getTotalElements());
        response.put("index", index);

        if (index < total) {
            response.put("next", "/teams/hal?index=" + (index + count));
            response.put("final", "/teams/hal?index=" + (total - 1) * count + "&count=" + count);
        }

        if (index > 0) {
            response.put("prev", "/teams/hal?index=" + (index - count));
            response.put("first", "/teams/hal?index=0");
        }

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}