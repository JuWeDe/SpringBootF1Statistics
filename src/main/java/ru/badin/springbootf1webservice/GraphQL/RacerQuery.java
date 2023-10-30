//package ru.badin.springbootf1webservice.GraphQL;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//import org.springframework.stereotype.Component;
//import ru.badin.springbootf1webservice.DTO.RacerDto;
//import ru.badin.springbootf1webservice.Services.RacerServiceImpl;
//
//import java.util.List;
//
//@Component
//public class RacerQuery implements GraphQLQueryResolver {
//    private RacerServiceImpl service;
//    public RacerQuery(RacerServiceImpl service) {
//        this.service = service;
//    }
//
//    public List<RacerDto> getRacers() {
//        return service.getAllRacers();
//    }
//
//    public RacerDto getRacer(long id) {
//        return service.getRacerById(id);
//    }
//}
//
//
//
//
//
