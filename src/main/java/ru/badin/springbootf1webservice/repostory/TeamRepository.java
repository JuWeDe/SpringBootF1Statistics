package ru.badin.springbootf1webservice.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.badin.springbootf1webservice.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    // Используем магию! ¯\_(ツ)_/¯

}