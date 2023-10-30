//package ru.badin.springbootf1webservice.GraphQL;
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import org.springframework.stereotype.Component;
//import ru.badin.springbootf1webservice.Services.CarServiceImpl;
//
//
//@Component
//public class CarMutation implements GraphQLMutationResolver {
//    public CarMutation(CarServiceImpl carService) {
//        this.carService = carService;
//    }
//
//    private CarServiceImpl carService;
//
//    public void createCar(String name, String engine, int hp, int carNumber) {
//        carService.createCar(name, engine, hp, carNumber);
//    }
//
//}
