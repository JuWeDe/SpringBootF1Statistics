package ru.badin.springbootf1webservice.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.badin.springbootf1webservice.model.Car;


public interface CarRepository extends JpaRepository<Car, Long> {

    // Используем магию! ¯\_(ツ)_/¯

}
