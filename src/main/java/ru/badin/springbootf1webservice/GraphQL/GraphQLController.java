//package ru.badin.springbootf1webservice.GraphQL;
//
//import graphql.ExecutionInput;
//import graphql.ExecutionResult;
//import graphql.GraphQL;
//import graphql.scalars.ExtendedScalars;
//import graphql.schema.GraphQLSchema;
//import graphql.schema.idl.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import ru.badin.springbootf1webservice.Services.CarService;
//import ru.badin.springbootf1webservice.Services.RacerService;
//import ru.badin.springbootf1webservice.Services.TeamService;
//import ru.badin.springbootf1webservice.model.Car;
//import ru.badin.springbootf1webservice.model.Racer;
//import ru.badin.springbootf1webservice.model.Team;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.Map;
//
//@RestController
//public class GraphQLController {
//    private final GraphQL graphQL;
//
//    public GraphQLController(CarService carService, RacerService racerService, TeamService teamService) throws IOException, FileNotFoundException {
//        SchemaParser schemaParser = new SchemaParser();
//        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(new FileInputStream("src/main/resources/schema.graphqls"));
//        SchemaGenerator schemaGenerator = new SchemaGenerator();
//        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, buildWiring(carService, racerService, teamService));
//        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
//    }
//
//    private RuntimeWiring buildWiring(CarService carService, RacerService racerService, TeamService teamService) {
//        return RuntimeWiring.newRuntimeWiring()
//                .type(TypeRuntimeWiring.newTypeWiring("Query")
//                        .dataFetcher("getCars", environment -> carService.getAllCars())
//                        .dataFetcher("getRacers", environment -> racerService.getAllRacers())
//                        .dataFetcher("getTeams", environment -> teamService.getAllTeams()))
//                .type(TypeRuntimeWiring.newTypeWiring("Mutation")
//                        .dataFetcher("createCar", environment -> {
//                            String name = environment.getArgument("name");
//                            String engine = environment.getArgument("engine");
//                            int hp = environment.getArgument("hp");
//                            int carNumber = environment.getArgument("carNumber");
//                            Car car = new Car();
//                            car.setEngine(engine);
//                            car.setHp(hp);
//                            car.setName(name);
//                            car.setCarNumber(carNumber);
//                            return carService.createCar(car);
//                        })
//                        .dataFetcher("createRacer", environment -> {
//                            String name = environment.getArgument("name");
//                            LocalDate dateOfBirth = environment.getArgument("dateOfBirth");
//                            int wins = environment.getArgument("wins");
//                            int championships = environment.getArgument("championships");
//                            Double points = environment.getArgument("points");
//                            Racer racer =new Racer();
//                            racer.setWins(wins);
//                            racer.setPoints(points);
////                            racer.setDateOfBirth(dateOfBirth);
//                            racer.setChampionships(championships);
//                            racer.setName(name);
//
//                            return racerService.createRacer(racer);
//                        })
//                        .dataFetcher("createTeam", environment -> {
//                            String name = environment.getArgument("name");
//                            Double points = environment.getArgument("points");
//                            String teamPrinciple = environment.getArgument("teamPrinciple");
//                            Team team = new Team();
//                            team.setName(name);
//                            team.setPoints(points);
//                            team.setTeamPrinciple(teamPrinciple);
//                            return teamService.createTeam(team);
//                        }))
//                .scalar(ExtendedScalars.Date)
//                .build();
//    }
//
//
//    @PostMapping(value = "/graphql")
//    public ResponseEntity<Object> graphql(@RequestBody Map<String, Object> body) {
//        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
//                .query((String) body.get("query"))
//                .operationName((String) body.get("operationName"))
//                .variables((Map<String, Object>) body.get("variables"))
//                .build();
//        ExecutionResult executionResult = graphQL.execute(executionInput);
//        return ResponseEntity.ok(executionResult.toSpecification());
//    }
//}