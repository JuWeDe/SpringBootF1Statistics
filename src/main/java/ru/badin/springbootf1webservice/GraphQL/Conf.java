package ru.badin.springbootf1webservice.GraphQL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf {
    @Bean
    public graphql.schema.GraphQLScalarType extendedScalarLong() {
        return graphql.scalars.ExtendedScalars.GraphQLLong;
    }
}
