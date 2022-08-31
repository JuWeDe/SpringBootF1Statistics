package ru.badin.springbootf1webservice.rest;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.model.Car;
import ru.badin.springbootf1webservice.model.Team;
import ru.badin.springbootf1webservice.repostory.CarRepository;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import java.util.Map;

@RestController
public class CarController {

    private final CarRepository cr;
    private final TeamRepository tr;

    public CarController(CarRepository cr, TeamRepository tr) {
        this.cr = cr;
        this.tr = tr;
    }

    @GetMapping("/get_cars")
    Iterable<Car> getCars() {
        return cr.findAll();
    }


    @DeleteMapping("/remove_by_id/{id}")
    void deleteCar(@PathVariable Long id) {
        cr.deleteById(id);
    }

    @PutMapping("/update_car/{id}")
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
        Car car = cr.findById(id).get();
        car.setHp(Integer.parseInt(body.get("hp")));
        car.setName(body.get("name"));
        car.setTeamId((long) Integer.parseInt(body.get("teamId")));
        cr.save(car);
        return car;
    }

    @PostMapping("/add_car")
    public Car create(@RequestBody Map<String, String> body) {
        Car car = new Car();
        String name = body.get("name");
        int teamId = Integer.parseInt(body.get("teamId"));
        int hp = Integer.parseInt(body.get("hp"));
        car.setTeamId((long) teamId);
        car.setName(name);
        car.setHp(hp);

        return cr.save(car);

    }

}

 */



