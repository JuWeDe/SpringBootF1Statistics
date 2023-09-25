package ru.badin.springbootf1webservice.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double points;
    private String teamPrinciple;

    @OneToMany(mappedBy = "team")
    private List<Racer> racers;

    @OneToMany(mappedBy = "team")
    private List<Car> cars;
}
