package ru.badin.springbootf1webservice.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity(name = "racers")
public class Racer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;
    private int age;
    private int wins;
    private int championships;
    private Double points;
    private Double pointsInSeason;


    @ManyToOne
    private Team team;
        public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }





    public Double getPointsInSeason() {
        return pointsInSeason;
    }

    public void setPointsInSeason(Double pointsInSeason) {
        this.pointsInSeason = pointsInSeason;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Racer() {
    }
}



