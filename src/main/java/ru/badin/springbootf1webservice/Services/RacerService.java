package ru.badin.springbootf1webservice.Services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.repostory.RacerRepository;

import java.util.List;

@Service
public class RacerService {
    private final RacerRepository racerRepository;

    public RacerService(RacerRepository racerRepository) {
        this.racerRepository = racerRepository;
    }

    public Page<Racer> getRacers(int index, int count){
        Pageable pageable = PageRequest.of(index, count);
        return racerRepository.findAll(pageable);
    }

    public Racer createRacer(Racer racer) {
        return racerRepository.save(racer);
    }

    public Racer getRacerById(Long id) {
        return racerRepository.findById(id).orElse(null);
    }

    public List<Racer> getAllRacers() {
        return racerRepository.findAll();
    }

    public Racer updateRacer(Long id, Racer updateRacer) {
        Racer racer = getRacerById(id);
        if (racer != null) {
            racer.setName(updateRacer.getName());
            racer.setTeam(updateRacer.getTeam());
            racer.setChampionships(updateRacer.getChampionships());
            racer.setPoints(updateRacer.getPoints());
            racer.setDateOfBirth(updateRacer.getDateOfBirth());
            racer.setPointsInSeason(updateRacer.getPointsInSeason());
            racer.setWins(updateRacer.getWins());
            return racerRepository.save(racer);
        }
        return null;
    }

    public void deleteRacer(Long id) {
        Racer racer = getRacerById(id);
        if (racer != null) {
            racerRepository.delete(racer);
        }
    }
}