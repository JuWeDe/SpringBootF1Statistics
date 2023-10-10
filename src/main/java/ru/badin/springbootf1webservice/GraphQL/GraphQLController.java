//package ru.badin.springbootf1webservice.GraphQL;
//
//import graphql.ExecutionInput;
//import graphql.ExecutionResult;
//import graphql.kickstart.servlet.osgi.GraphQLProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//@RestController
//@RequestMapping("/api/graphql")
//public class GraphQLController {
//    @Autowired
//    private GraphQLProvider graphQLProvider;
//
//    @PostMapping
//    public CompletableFuture<ResponseEntity<ExecutionResult>> graphql(@RequestBody Map<String, Object> request) {
//        String query = (String) request.get("query");
//        Map<String, Object> variables = (Map<String, Object>) request.get("variables");
//        String operationName = (String) request.get("operationName");
//
//        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
//                .query(query)
//                .variables(variables)
//                .operationName(operationName)
//                .build();
//
//        return CompletableFuture.supplyAsync(() -> graphQLProvider.getGraphQL().execute(executionInput))
//                .thenApply(result -> ResponseEntity.ok(result));
//    }
//}