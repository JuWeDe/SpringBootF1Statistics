//package ru.badin.springbootf1webservice.GraphQL;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.badin.springbootf1webservice.Services.CarService;
//import ru.badin.springbootf1webservice.model.Car;
//
//import java.util.List;
//
//@Component
//public class CarQuery implements GraphQLQueryResolver {
//    private final CarService carService;
//
//    public CarQuery(CarService carService) {
//        this.carService = carService;
//    }
//
//    public List<Car> getCars(){
//        return carService.getAllCars();
//    }
//    public Car getCar(long id) {
//        return carService.getCarById(id);
//    }
//
//}
