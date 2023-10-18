package ru.badin.springbootf1webservice.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.HAL.HAL;
import ru.badin.springbootf1webservice.Services.CarService;
import ru.badin.springbootf1webservice.model.Car;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private static final int PAGE_SIZE = 10;
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }


    @GetMapping("/hal")
    public ResponseEntity<Map<String, Object>> getAllCarsWithHAL(@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int count) {
        return ResponseEntity.ok(carService.getAllCarsWithHAL(index, count));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);

        if (car == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", car.getId());
        responseBody.put("name", car.getName());
        responseBody.put("carNumber", car.getCarNumber());
        responseBody.put("engine", car.getEngine());
        responseBody.put("hp", car.getHp());


        responseBody.put("update", HAL.addHypermediaLink("/api/cars/" + car.getId(), "update", "PUT", "Update car"));
        responseBody.put("delete", HAL.addHypermediaLink("/api/cars/" + car.getId(), "delete", "DELETE", "Delete car"));

        return ResponseEntity.ok(responseBody);
    }


    @PostMapping
    public Car createCar(@RequestBody Map<String, String> body) {
        Car car = new Car();
        String name = body.get("name");
        String engine = body.get("engine");
        int hp = Integer.parseInt(body.get("hp"));
        int carNumber = Integer.parseInt(body.get("carNumber"));
        car.setName(name);
        car.setCarNumber(carNumber);
        car.setHp(hp);
        car.setEngine(engine);


        return carService.createCar(car);

    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {

        Car car = carService.getCarById(id);
        if (carService.getCarById(id) != null) {

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
        carService.updateCar(id, car);
        return car;
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}