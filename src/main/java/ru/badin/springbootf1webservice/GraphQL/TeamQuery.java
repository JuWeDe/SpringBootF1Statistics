//package ru.badin.springbootf1webservice.GraphQL;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//import org.springframework.stereotype.Component;
//import ru.badin.springbootf1webservice.DTO.TeamDto;
//import ru.badin.springbootf1webservice.Services.TeamServiceImpl;
//
//import java.util.List;
//
//@Component
//public class TeamQuery implements GraphQLQueryResolver {
//    public TeamQuery(TeamServiceImpl service) {
//        this.service = service;
//    }
//
//    private TeamServiceImpl service;
//
//    public List<TeamDto> getTeams() {
//        return service.getAllTeams();
//    }
//
//    public TeamDto getTeam(long id) {
//        return service.getTeamById(id);
//    }
//}
//
//
