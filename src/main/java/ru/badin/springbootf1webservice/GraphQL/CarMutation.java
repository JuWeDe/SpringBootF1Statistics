//package ru.badin.springbootf1webservice.GraphQL;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.badin.springbootf1webservice.Services.CarService;
//import ru.badin.springbootf1webservice.model.Car;
//
//@Component
//public class CarMutation {
//
//    private CarService carService;
//
//    public Car createCar(String name, String engine, int hp, int carNumber) {
//        Car car = new Car();
//        car.setName(name);
//        car.setCarNumber(carNumber);
//        car.setHp(hp);
//        car.setEngine(engine);
//        return carService.createCar(car);
//    }
//}
