package ru.badin.springbootf1webservice.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.HAL.HAL;
import ru.badin.springbootf1webservice.Services.RacerService;
import ru.badin.springbootf1webservice.Services.TeamService;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.model.Team;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/racers")
public class RacerController {
    private final RacerService racerService;
    private final TeamService teamService;

    @GetMapping("/hal")
    public ResponseEntity<Map<String, Object>> getAllRacersWithHAL(@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int count) {
        return ResponseEntity.ok(racerService.getAllRacersWithHAL(index, count));
    }

    public RacerController(RacerService racerService, TeamService teamService) {
        this.racerService = racerService;
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Racer> createRacer(@RequestBody Racer racer) {
        Racer createdRacer = racerService.createRacer(racer);
        return ResponseEntity.ok(createdRacer);
    }


    @PostMapping("/{racerId}/addCarToRacer/{carId}")
    public ResponseEntity<String> assignRacerToCar(@PathVariable Long racerId, @PathVariable Long carId) {
        racerService.addCarToRacer(racerId, carId);
        return ResponseEntity.ok("Car added to racer successfully");
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
            Team team = updatedRacer.getTeam();
            if (team != null) {
                team = teamService.getTeamById(team.getId());
                racer.setTeam(team);
            }
            return racerService.updateRacer(id, racer);
        }
        return null;
    }


    @GetMapping
    public List<Racer> getAllRacers() {
        return racerService.getAllRacers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getRacerById(@PathVariable Long id) {
        Racer racer = racerService.getRacerById(id);

        if (racer == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", racer.getId());
        responseBody.put("name", racer.getName());
        responseBody.put("dateOfBirth", racer.getDateOfBirth());
        responseBody.put("wins", racer.getWins());
        responseBody.put("championships", racer.getChampionships());
        responseBody.put("points", racer.getPoints());

        responseBody.put("update", HAL.addHypermediaLink("/api/racers/" + racer.getId(), "update", "PUT", "Update racer"));
        responseBody.put("delete", HAL.addHypermediaLink("/api/racers/" + racer.getId(), "delete", "DELETE", "Delete racer"));

        return ResponseEntity.ok(responseBody);
    }


    @DeleteMapping("/{id}")
    public void deleteRacer(@PathVariable Long id) {
        racerService.deleteRacer(id);
    }
}