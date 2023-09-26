package ru.badin.springbootf1webservice.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.badin.springbootf1webservice.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Используем магию! ¯\_(ツ)_/¯

}
