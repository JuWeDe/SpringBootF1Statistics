package ru.badin.springbootf1webservice.rest;



import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.Services.RacerService;
import ru.badin.springbootf1webservice.Services.TeamService;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.model.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/racers")
public class RacerController {

    private static final int PAGE_SIZE = 10;
    private final RacerService racerService;
    private final TeamService teamService;

    public RacerController(RacerService racerService, TeamService teamService) {
        this.racerService = racerService;
        this.teamService = teamService;
    }

    @PostMapping
    public Racer addRacer(@RequestBody Racer racer) {
        return racerService.createRacer(racer);
    }

    @PutMapping("/{id}")
    public Racer updateRacer(@PathVariable Long id, @RequestBody Racer updatedRacer) {
        Racer racer = racerService.getRacerById(id);
        if (racer != null) {
            racer.setName(updatedRacer.getName());
            racer.setDateOfBirth(updatedRacer.getDateOfBirth());
            racer.setWins(updatedRacer.getWins());
            racer.setChampionships(updatedRacer.getChampionships());
            racer.setPoints(updatedRacer.getPoints());
            racer.setPointsInSeason(updatedRacer.getPointsInSeason());
            Team team = updatedRacer.getTeam();
            if (team != null) {
                team = teamService.getTeamById(team.getId());
                racer.setTeam(team);
            }
            return racerService.updateRacer(id, racer);
        }
        return null;
    }
    @GetMapping("/hal")
    public ResponseEntity<Map<String, Object>> getRacers(@RequestParam(defaultValue = "0") int index,
                                                       @RequestParam(defaultValue = "25") int count) {
        Page<Racer> racers = racerService.getRacers(index, count);

        List<Racer> racerList = racers.getContent();
        Map<String, Object> response = new HashMap<>();
        int total = racers.getTotalPages();
        response.put("items", racerList);
        response.put("count", racerList.size());
        response.put("total", racers.getTotalElements());
        response.put("index", index);

        if (index < total) {
            response.put("next", "/racers/hal?index=" + (index + count));
//            here
            response.put("final", "/racers/hal?index=" + (total - (total % count)) + "&count=" + count);
        }

        if (index > 0) {
            response.put("prev", "/racers/hal?index=" + (index - count));
            response.put("first", "/racers/hal?index=0");
        }


        return ResponseEntity.ok(response);
    }
    @GetMapping
    public List<Racer> getAllRacers() {
        return racerService.getAllRacers();
    }

    @GetMapping("/{id}")
    public Racer getRacerById(@PathVariable Long id) {
        return racerService.getRacerById(id);
    }

//    @PostMapping
//    public Racer createRacer(@RequestBody Racer racer) {
//        return racerService.createRacer(racer);
//    }

//    @PutMapping("/{id}")
//    public Racer updateRacer(@PathVariable Long id, @RequestBody Racer racer) {
//        return racerService.updateRacer(id, racer);
//    }

    @DeleteMapping("/{id}")
    public void deleteRacer(@PathVariable Long id) {
        racerService.deleteRacer(id);
    }
}