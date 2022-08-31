package ru.badin.springbootf1webservice.model;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private Double points;
    private Double pointsInSeason;
    private Long carId;
    private String teamPrinciple;


    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getPointsInSeason() {
        return pointsInSeason;
    }

    public void setPointsInSeason(Double pointsInSeason) {
        this.pointsInSeason = pointsInSeason;
    }


    public String getTeamPrinciple() {
        return teamPrinciple;
    }

    public void setTeamPrinciple(String teamPrinciple) {
        this.teamPrinciple = teamPrinciple;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
