package ru.badin.springbootf1webservice.Services;


import com.vaadin.flow.router.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.model.Car;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.repostory.CarRepository;
import ru.badin.springbootf1webservice.repostory.RacerRepository;

import java.util.List;


@Service
public class CarService {
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;



    public CarService(CarRepository carRepository, RacerRepository racerRepository) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
    }


    public Page<Car> getCars(int index, int count) {
        Pageable pageable = PageRequest.of(index, count);
        return carRepository.findAll(pageable);
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