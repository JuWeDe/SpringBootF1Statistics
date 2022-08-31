package ru.badin.springbootf1webservice.rest;

import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.model.Car;

import ru.badin.springbootf1webservice.repostory.CarRepository;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import java.util.Collections;
import java.util.Map;

@RestController
public class CarController {

    private final CarRepository cr;
    private final TeamRepository tr;

    public CarController(CarRepository cr, TeamRepository tr) {
        this.cr = cr;
        this.tr = tr;
    }

    @GetMapping("get_car_by_id/{id}")
    Iterable<Car> getCarById(@PathVariable Long id) {
        return cr.findAllById(Collections.singleton(id));
    }

    @GetMapping("/get_cars")
    Iterable<Car> getCars() {
        return cr.findAll();
    }


    @DeleteMapping("/remove_car_by_id/{id}")
    void deleteCar(@PathVariable Long id) {
        Car car = cr.findById(id).get();
        if (cr.findById(id).isPresent())
        {
            cr.delete(car);
        }

    }

    @PutMapping("/update_car/{id}")
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {

        Car car = cr.findById(id).get();
        if (cr.findById(id).isPresent()) {

            if (body.get("name") != null) {
                car.setName(body.get("name"));
            }
            if (body.get("engine") != null) {
                car.setEngine(body.get("engine"));
            }
            if (body.get("hp") != null) {
                car.setHp(Integer.parseInt(body.get("hp")));
            }
        }


        cr.save(car);
        return car;
    }

    @PostMapping("/add_car")
    public Car create(@RequestBody Map<String, String> body) {
        Car car = new Car();
        String name = body.get("name");
        String engine = body.get("engine");
        int hp = Integer.parseInt(body.get("hp"));
        car.setName(name);
        car.setHp(hp);
        car.setEngine(engine);


        return cr.save(car);

    }

}





