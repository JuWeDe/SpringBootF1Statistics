package ru.badin.springbootf1webservice.Services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.HAL.HAL;
import ru.badin.springbootf1webservice.model.Car;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.model.Team;
import ru.badin.springbootf1webservice.repostory.CarRepository;
import ru.badin.springbootf1webservice.repostory.RacerRepository;
import java.util.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CarService {
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;

    public CarService(CarRepository carRepository, RacerRepository racerRepository) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
    }


    public Page<Car> getAllCars(int index, int count) {
        PageRequest pageable = PageRequest.of(index, count);
        return carRepository.findAll(pageable);
    }
    public Map<String, Object> getAllCarsWithHAL(int index, int count) {
        Page<Car> carPage = getAllCars(index, count);
        String baseUrl = "/api/cars";
        int pageNumber = carPage.getNumber();
        int pageSize = carPage.getSize();
        int total = carPage.getTotalPages();

        List<Map<String, Object>> embeddedRacers = carPage.getContent().stream()
                .map(this::createCarResource)
                .collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("_embedded", Collections.singletonMap("racers", embeddedRacers));
        response.put("_links", HAL.paginateAsDictionary(baseUrl, pageNumber, pageSize, total));

        return response;
    }
    private Map<String, Object> createCarResource(Car car) {
        Map<String, Object> carResource = new LinkedHashMap<>();
        carResource.put("_links", createCarLinks(car));
        carResource.put("id", car.getId());
        carResource.put("name", car.getName());
        carResource.put("carNumber", car.getCarNumber());
        carResource.put("engine", car.getEngine());
        carResource.put("hp", car.getHp());

        return carResource;
    }

    private Map<String, Object> createCarLinks(Car car) {
        String baseUrl = "/api/cars";
        Long carId = car.getId();
        Map<String, Object> links = new LinkedHashMap<>();
        links.put("self", Collections.singletonMap("href", baseUrl + "/" + carId));
        links.put("team", Collections.singletonMap("href", baseUrl + "/" + carId + "/team"));
        links.put("racer", Collections.singletonMap("href", baseUrl + "/" + carId + "/racer"));
        return links;
    }


    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public void updateCar(Long id, Car updatedCar) {
        Car car = getCarById(id);
        if (car != null) {
            car.setName(updatedCar.getName());
            car.setEngine(updatedCar.getEngine());
            car.setHp(updatedCar.getHp());
            carRepository.save(car);
        }
    }

    public void deleteCar(Long id) {
        Car car = getCarById(id);
        if (car != null) {
            carRepository.delete(car);
        }
    }


}