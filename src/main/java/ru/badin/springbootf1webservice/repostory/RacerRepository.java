package ru.badin.springbootf1webservice.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.badin.springbootf1webservice.model.Racer;


@Repository
public interface RacerRepository extends JpaRepository<Racer, Long> {
    // Используем магию! ¯\_(ツ)_/¯

}


