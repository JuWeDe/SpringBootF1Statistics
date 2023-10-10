//package ru.badin.springbootf1webservice.GraphQL;
//
//import com.google.common.collect.ImmutableMap;
//import graphql.schema.DataFetcher;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Map;
//
//public class GraphQLDataFetchers {
//    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//    private static final List<Team> teams = List.of(
//            new Team(1L, "Team 1", 10.5f, "Principle 1"),
//            new Team(2L, "Team 2", 8.2f, "Principle 2"),
//            new Team(3L, "Team 3", 6.7f, "Principle 3")
//    );
//
//    private static final List<Racer> racers = List.of(
//            new Racer(1L, "Racer 1", LocalDate.parse("1995-01-01", dateFormatter), 5, 2, 8.5f, teams.get(0)),
//            new Racer(2L, "Racer 2", LocalDate.parse("1990-05-05", dateFormatter), 3, 1, 6.3f, teams.get(1)),
//            new Racer(3L, "Racer 3", LocalDate.parse("1992-09-10", dateFormatter), 4, 1, 7.1f, teams.get(1)),
//            new Racer(4L, "Racer 4", LocalDate.parse("1988-03-20", dateFormatter), 2, 0, 5.2f, teams.get(2))
//    );
//
//    private static final List<Car> cars = List.of(
//            new Car(1L, "Car 1", "Engine 1", 300, 10, teams.get(0)),
//            new Car(2L, "Car 2", "Engine 2", 350, 20, teams.get(1)),
//            new Car(3L, "Car 3", "Engine 3", 400, 30, teams.get(1)),
//            new Car(4L, "Car 4", "Engine 4", 250, 40, teams.get(2))
//    );
//
//    public static DataFetcher<List<Team>> getAllTeamsDataFetcher() {
//        return dataFetchingEnvironment -> teams;
//    }
//
//    public static DataFetcher<Team> getTeamByIdDataFetcher() {
//        return dataFetchingEnvironment -> {
//            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
//            Long id = Long.parseLong(arguments.get("id").toString());
//            return teams.stream().filter(team -> team.getId().equals(id)).findFirst().orElse(null);
//        };
//    }
//
//    public static DataFetcher<List<Racer>> getAllRacersDataFetcher() {
//        return dataFetchingEnvironment -> racers;
//    }
//
//    public static DataFetcher<Racer> getRacerByIdDataFetcher() {
//        return dataFetchingEnvironment -> {
//            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
//            Long id = Long.parseLong(arguments.get("id").toString());
//            return racers.stream().filter(racer -> racer.getId().equals(id)).findFirst().orElse(null);
//        };
//    }
//
//    public static DataFetcher<List<Car>> getAllCarsDataFetcher() {
//        return dataFetchingEnvironment -> cars;
//    }
//
//    public static DataFetcher<Car> getCarByIdDataFetcher() {
//        return dataFetchingEnvironment -> {
//            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
//            Long id = Long.parseLong(arguments.get("id").toString());
//            return cars.stream().filter(car -> car.getId().equals(id)).findFirst().orElse(null);
//        };
//    }
//
//    public static DataFetcher<LocalDate> getLocalDateDataFetcher() {
//        return dataFetchingEnvironment -> {
//            String dateString = dataFetchingEnvironment.getArgument("dateOfBirth");
//            return LocalDate.parse(dateString, dateFormatter);
//        };
//    }
//}