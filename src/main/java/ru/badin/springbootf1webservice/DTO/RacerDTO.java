package ru.badin.springbootf1webservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RacerDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private int wins;
    private int championships;
    private Double points;
}