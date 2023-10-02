package ru.badin.springbootf1webservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String name;
    private String engine;
    private int hp;
    private int carNumber;
}