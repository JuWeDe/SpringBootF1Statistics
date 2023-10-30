package ru.badin.springbootf1webservice.DTO;


import ru.badin.springbootf1webservice.model.Car;

public class CarDto {
    private Long id;
    private String name;
    private String engine;
    private int hp;
    private int carNumber;
    private Long teamId;


    public CarDto() {

    }

    public CarDto(Car car) {
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

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", engine='" + engine + '\'' +
                ", hp=" + hp +
                ", carNumber=" + carNumber +
                '}';
    }
}