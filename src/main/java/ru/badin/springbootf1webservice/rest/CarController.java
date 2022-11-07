package ru.badin.springbootf1webservice.rest;

import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.model.Car;

import ru.badin.springbootf1webservice.repostory.CarRepository;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import java.util.Collections;
import java.util.Map;

@RestController
public class CarController {

    private final CarRepository carRepository;
    private final TeamRepository teamRepository;

    public CarController(CarRepository carRepository, TeamRepository teamRepository) {
        this.carRepository = carRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("car/{id}")
    Iterable<Car> getCarById(@PathVariable Long id) {
        return carRepository.findAllById(Collections.singleton(id));
    }

    @GetMapping("/car")
    Iterable<Car> getCars() {
        return carRepository.findAll();
    }


    @DeleteMapping("/car/{id}")
    void deleteCar(@PathVariable Long id) {
        Car car = carRepository.findById(id).get();
        if (carRepository.findById(id).isPresent())
        {
            carRepository.delete(car);
        }

    }

    @PutMapping("/car/{id}")
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {

        Car car = carRepository.findById(id).get();
        if (carRepository.findById(id).isPresent()) {

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


        carRepository.save(car);
        return car;
    }

    @PostMapping("/car")
    public Car create(@RequestBody Map<String, String> body) {
        Car car = new Car();
        String name = body.get("name");
        String engine = body.get("engine");
        int hp = Integer.parseInt(body.get("hp"));
        car.setName(name);
        car.setHp(hp);
        car.setEngine(engine);


        return carRepository.save(car);

    }

}





