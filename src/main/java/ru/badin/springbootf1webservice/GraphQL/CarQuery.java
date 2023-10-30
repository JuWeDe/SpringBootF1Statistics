//package ru.badin.springbootf1webservice.GraphQL;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//import org.springframework.stereotype.Component;
//import ru.badin.springbootf1webservice.DTO.CarDto;
//import ru.badin.springbootf1webservice.Services.CarServiceImpl;
//
//import java.util.List;
//
//@Component
//public class CarQuery implements GraphQLQueryResolver {
//
//    private CarServiceImpl service;
//
//    public List<CarDto> getCars() {
//        return service.getAllCars();
//    }
//
//    public CarDto getCar(long id) {
//        return service.getCarById(id);
//    }
//
//}
