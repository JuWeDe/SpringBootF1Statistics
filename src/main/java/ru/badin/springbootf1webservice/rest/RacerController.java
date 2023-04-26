package ru.badin.springbootf1webservice.rest;

import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.repostory.RacerRepository;

import java.sql.Date;
import java.util.Collections;
import java.util.Map;

@RestController
public class RacerController {

    private final RacerRepository racerRepository;

    public RacerController(RacerRepository rp) {
        this.racerRepository = rp;
    }

    @GetMapping("/racer")
    Iterable<Racer> getRacers() {
        return racerRepository.findAll();
    }

    @GetMapping("/racer/{id}")
    Iterable<Racer> getRacerById(@PathVariable Long id) {
        return racerRepository.findAllById(Collections.singleton(id));
    }


    @DeleteMapping("/racer/{id}")
    void deleteRacer(@PathVariable Long id) {
        Racer racer = racerRepository.findById(id).get();
        if (racerRepository.findById(id).isPresent()) {
            racerRepository.delete(racer);
        }
    }

    @PutMapping("/racer/{id}")
    public Racer updateRacer(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {

        Racer racer = racerRepository.findById(id).get();
        if (racerRepository.findById(id).isPresent()) {
            if (body.get("name") != null) {
                racer.setName(body.get("name"));

            }
            if (body.get("dateOfBirth") != null) {
                racer.setDateOfBirth(Date.valueOf(body.get("dateOfBirth")));
            }
            if (body.get("pointsInSeason") != null) {
                racer.setPointsInSeason(Double.valueOf(body.get("pointsInSeason")));

            }

            if (body.get("points") != null) {
                racer.setPoints(Double.valueOf(body.get("points")));
            }
            if (body.get("championships") != null) {
                racer.setChampionships(Integer.parseInt(body.get("championships")));

            }
            if (body.get("wins") != null) {
                racer.setWins(Integer.parseInt(body.get("wins")));
            }
        }

        racerRepository.save(racer);
        return racer;
    }

    @PostMapping("/racer")
    public Racer create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        Date birthDate = Date.valueOf((body.get("birthDate")));
        int wins = Integer.parseInt(body.get("wins"));
        int championships = Integer.parseInt(body.get("championships"));
        Double points = Double.valueOf(body.get("points"));
        Double pointsInSeason = Double.valueOf(body.get("pointsInSeason"));
        int teamID = Integer.parseInt(body.get("teamID"));
        Racer racer = new Racer();
        racer.setName(name);
        racer.setDateOfBirth(birthDate);
        racer.setWins(wins);
        racer.setChampionships(championships);
        racer.setPoints(points);
        racer.setPointsInSeason(pointsInSeason);
        //racer.setTeamID(teamID);

        return racerRepository.save(racer);

    }


}



