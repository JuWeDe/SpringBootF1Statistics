package ru.badin.springbootf1webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class SpringBootF1WebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootF1WebServiceApplication.class, args);
    }

}
