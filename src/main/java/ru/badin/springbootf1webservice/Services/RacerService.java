package ru.badin.springbootf1webservice.Services;


import com.vaadin.flow.router.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.model.Car;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.model.Team;
import ru.badin.springbootf1webservice.repostory.CarRepository;
import ru.badin.springbootf1webservice.repostory.RacerRepository;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RacerService {
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;
    private final TeamRepository teamRepository;

    public RacerService(RacerRepository racerRepository, CarRepository carRepository, TeamRepository teamRepository) {
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.teamRepository = teamRepository;
    }

//    public Racer assignCarToRacer(Long racerId, Long carId) {
//        Racer racer = racerRepository.findById(racerId).orElseThrow(() -> new NotFoundException("Racer not found"));
//        Car car = carRepository.findById(carId).orElseThrow(() -> new NotFoundException("Car not found"));
//        racer.setCar(car);
//        return racerRepository.save(racer);
//    }

    @Transactional
    public void addCarToRacer(Long racerId, Long carId) {
        Racer racer = racerRepository.findById(racerId)
                .orElseThrow(() -> new NotFoundException("Racer not found with id: " + racerId));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car not found with id: " + carId));

        racer.setCar(car);
        racerRepository.save(racer);
    }
    public Racer assignRacerToTeam(Long racerId, Long teamId) {
        Racer racer = racerRepository.findById(racerId).orElseThrow(() -> new NotFoundException("Racer not found"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new NotFoundException("Team not found"));
        racer.setTeam(team);
        return racerRepository.save(racer);
    }
    public Racer updateRacer(Long id, Racer updateRacer) {
        Racer racer = getRacerById(id);
        if (racer != null) {
            racer.setName(updateRacer.getName());
            racer.setChampionships(updateRacer.getChampionships());
            racer.setPoints(updateRacer.getPoints());
            racer.setDateOfBirth(updateRacer.getDateOfBirth());
            racer.setWins(updateRacer.getWins());
            return racerRepository.save(racer);
        }
        return null;
    }


    public Page<Racer> getRacers(int index, int count) {
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


    @Transactional
    public void deleteRacer(Long racerId) {
        Racer racer = racerRepository.findById(racerId)
                .orElseThrow(() -> new NotFoundException("Racer not found with id: " + racerId));

        if (racer.getCar() != null) {
            Car car = racer.getCar();
            car.setTeam(null);
            racer.setCar(null);
            carRepository.save(car);
        }

        if (racer.getTeam() != null) {
            Team team = racer.getTeam();
            team.getRacers().remove(racer);
            racer.setTeam(null);
            teamRepository.save(team);
        }

        racerRepository.delete(racer);
    }

}