package ru.badin.springbootf1webservice.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.badin.springbootf1webservice.DTO.CarDto;
import ru.badin.springbootf1webservice.Services.CarServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarServiceImpl carService;
    private final RabbitTemplate rabbitTemplate;

    public CarController(CarServiceImpl carService, RabbitTemplate rabbitTemplate) {
        this.carService = carService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/hal")
    public Map<String, Object> getAllCarsHal(@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int count) {
        return carService.getAllCarsHal(index, count);
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDTO) {
        carService.createCar(carDTO);
        rabbitTemplate.convertAndSend("queueName", carDTO);
        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        CarDto CarDto = carService.getCarById(id);
        if (CarDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CarDto);
    }

    @PutMapping("/{id}")
    public void updateCar(@PathVariable Long id, @RequestBody CarDto carDTO) {
        carService.updateCar(id, carDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}