package ru.badin.springbootf1webservice;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class SpringBootF1WebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootF1WebServiceApplication.class, args);
    }
    @Bean
    public GraphQL graphQL(GraphQLSchema schema) {
        return GraphQL.newGraphQL(schema).build();
    }
}
