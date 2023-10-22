package ru.badin.springbootf1webservice.Services;


import ru.badin.springbootf1webservice.DTO.RacerDto;

import java.util.List;
import java.util.Map;

public interface RacerService {
    RacerDto getRacerById(Long id);

    List<RacerDto> getAllRacers();

    void createRacer(RacerDto RacerDto);

    void updateRacer(Long id, RacerDto RacerDto);

    void deleteRacer(Long id);

    Map<String, Object> getAllRacersHal(int index, int count);
}
