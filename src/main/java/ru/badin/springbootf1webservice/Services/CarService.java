//package ru.badin.springbootf1webservice.Services;
//
//
//import org.springframework.stereotype.Service;
//import ru.badin.springbootf1webservice.model.Car;
//import ru.badin.springbootf1webservice.repostory.carService;
//
//import java.util.List;
//
//
//@Service
//public class CarService {
//    private final carService carService;
//
//    public CarService(carService carService) {
//        this.carService = carService;
//    }
//
//    public List<Car> getAllCars() {
//        return carService.findAll();
//    }
//
//    public Car getCarById(Long id) {
//        return carService.findById(id).orElse(null);
//    }
//
//    public Car createCar(Car car) {
//        return carRepository.save(car);
//    }
//
//    public Car updateCar(Long id, Car updatedCar) {
//        Car car = getCarById(id);
//        if (car != null) {
//            car.setName(updatedCar.getName());
//            car.setEngine(updatedCar.getEngine());
//            car.setHp(updatedCar.getHp());
//            car.setTeam(updatedCar.getTeam());
//            return carRepository.save(car);
//        }
//        return null;
//    }
//
//    public void deleteCar(Long id) {
//        Car car = getCarById(id);
//        if (car != null) {
//            carRepository.delete(car);
//        }
//    }
//}