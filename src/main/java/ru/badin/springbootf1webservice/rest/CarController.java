package ru.badin.springbootf1webservice.rest;


import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.model.Car;
import ru.badin.springbootf1webservice.repostory.CarRepository;

import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarRepository carService;

    public CarController(CarRepository carService) {
        this.carService = carService;
    }

    @GetMapping
    public Iterable<Car> getAllCars() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.findById(id).get();
    }

    @PostMapping
    public Car createCar(@RequestBody Map<String, String> body) {
        Car car = new Car();
        String name = body.get("name");
        String engine = body.get("engine");
        int hp = Integer.parseInt(body.get("hp"));
        car.setName(name);
        car.setHp(hp);
        car.setEngine(engine);


        return carService.save(car);

    }

    @PutMapping("/car/{id}")
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {

        Car car = carService.findById(id).get();
        if (carService.findById(id).isPresent()) {

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


        carService.save(car);
        return car;
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
    }
}