package ru.badin.springbootf1webservice.rest;



import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.Services.RacerService;
import ru.badin.springbootf1webservice.model.Racer;

import java.util.List;

@RestController
@RequestMapping("/racers")
public class RacerController {
    private final RacerService racerService;

    public RacerController(RacerService racerService) {
        this.racerService = racerService;
    }

    @GetMapping
    public List<Racer> getAllRacers() {
        return racerService.getAllRacers();
    }

    @GetMapping("/{id}")
    public Racer getRacerById(@PathVariable Long id) {
        return racerService.getRacerById(id);
    }

    @PostMapping
    public Racer createRacer(@RequestBody Racer racer) {
        return racerService.createRacer(racer);
    }

    @PutMapping("/{id}")
    public Racer updateRacer(@PathVariable Long id, @RequestBody Racer racer) {
        return racerService.updateRacer(id, racer);
    }

    @DeleteMapping("/{id}")
    public void deleteRacer(@PathVariable Long id) {
        racerService.deleteRacer(id);
    }
}