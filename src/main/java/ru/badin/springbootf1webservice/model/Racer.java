package ru.badin.springbootf1webservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "racers")
public class Racer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private LocalDate dateOfBirth;
    private int wins;
    private int championships;
    private Double points;
    private Double pointsInSeason;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}

