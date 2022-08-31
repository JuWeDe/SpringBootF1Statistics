package ru.badin.springbootf1webservice.rest;

import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.repostory.RacerRepository;

import java.util.Collections;
import java.util.Map;

@RestController
public class RacerController {

    private final RacerRepository rp;

    public RacerController(RacerRepository rp) {
        this.rp = rp;
    }

    @GetMapping("/get_racers")
    Iterable<Racer> getRacers() {
        return rp.findAll();
    }

    @GetMapping("/get_racer_by_id/{id}")
    Iterable<Racer> getRacerById(@PathVariable Long id) {
        return rp.findAllById(Collections.singleton(id));
    }


    @DeleteMapping("/remove_racer_by_id/{id}")
    void deleteRacer(@PathVariable Long id) {
        Racer racer = rp.findById(id).get();
        rp.delete(racer);
    }

    @PutMapping("/update_racer/{id}")
    public Racer updateRacer(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {

        Racer racer = rp.findById(id).get();
        if (rp.findById(id).isPresent()) {
            if (body.get("name") != null) {
                racer.setName(body.get("name"));

            }
            if (body.get("age") != null) {
                racer.setAge(Integer.parseInt(body.get("age")));
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

        rp.save(racer);
        return racer;
    }

    @PostMapping("/add_racer")
    public Racer create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        int age = Integer.parseInt(body.get("age"));
        int wins = Integer.parseInt(body.get("wins"));
        int championships = Integer.parseInt(body.get("championships"));
        Double points = Double.valueOf(body.get("points"));
        Double pointsInSeason = Double.valueOf(body.get("pointsInSeason"));
        int teamID = Integer.parseInt(body.get("teamID"));
        Racer racer = new Racer();
        racer.setName(name);
        racer.setAge(age);
        racer.setWins(wins);
        racer.setChampionships(championships);
        racer.setPoints(points);
        racer.setPointsInSeason(pointsInSeason);
        //racer.setTeamID(teamID);

        return rp.save(racer);

    }


}



