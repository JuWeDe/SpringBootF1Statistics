package ru.badin.springbootf1webservice.rest;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.Services.CarService;
import ru.badin.springbootf1webservice.model.Car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
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
    public ResponseEntity<Map<String, Object>> getCars(@RequestParam(defaultValue = "0") int index,
                                                       @RequestParam(defaultValue = "25") int count) {
        Page<Car> cars = carService.getCars(index, count);

        List<Car> carList = cars.getContent();
        Map<String, Object> response = new HashMap<>();
        int total = cars.getTotalPages();
        response.put("items", carList);
        response.put("count", carList.size());
        response.put("total", cars.getTotalElements());
        response.put("index", index);

        if (index < total) {
            response.put("next", "/cars/hal?index=" + (index + count));
            response.put("final", "/cars/hal?index=" + (total - (total % count)) + "&count=" + count);
        }

        if (index > 0) {
            response.put("prev", "/cars/hal?index=" + (index - count));
            response.put("first", "/cars/hal?index=0");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
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


        return carService.createCar(car);

    }
//    @PostMapping
//    public Car createCar(@RequestBody Car car) {
//        return carService.save(car);
//    }

    @PutMapping("/car/{id}")
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
//    No services
//    @PutMapping("/{id}")
//    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
//        Car existingCar = carService.findById(id).orElse(null);
//        if (existingCar != null) {
//            existingCar.setName(updatedCar.getName());
//            existingCar.setEngine(updatedCar.getEngine());
//            existingCar.setHp(updatedCar.getHp());
//            existingCar.setTeam(updatedCar.getTeam());
//            return carService.save(existingCar);
//        }
//        return null;
//    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}