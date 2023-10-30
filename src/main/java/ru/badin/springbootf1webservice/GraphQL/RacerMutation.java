//package ru.badin.springbootf1webservice.GraphQL;
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import org.springframework.stereotype.Component;
//import ru.badin.springbootf1webservice.Services.RacerServiceImpl;
//
//
//@Component
//public class RacerMutation implements GraphQLMutationResolver {
//
//    public RacerMutation(RacerServiceImpl racerService) {
//        this.racerService = racerService;
//    }
//
//    private RacerServiceImpl racerService;
//
//    public void createRacer(String name, String dateOfBirth, int wins, int championships, float points) {
//        racerService.createRacer(name, dateOfBirth, wins, championships, points);
//    }
//}
