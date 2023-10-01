package ru.badin.springbootf1webservice.Services;

import com.vaadin.flow.router.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.model.Racer;
import ru.badin.springbootf1webservice.model.Team;
import ru.badin.springbootf1webservice.repostory.RacerRepository;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final RacerRepository racerRepository;

    public TeamService(TeamRepository teamRepository, RacerRepository racerRepository) {
        this.teamRepository = teamRepository;
        this.racerRepository = racerRepository;
    }


    public Page<Team> getTeams(int index, int count) {
        Pageable pageable = PageRequest.of(index, count);
        return teamRepository.findAll(pageable);
    }
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team updatedTeam) {
        Team team = getTeamById(id);
        if (team != null) {
            team.setName(updatedTeam.getName());
            team.setPoints(updatedTeam.getPoints());
            team.setTeamPrinciple(updatedTeam.getTeamPrinciple());
            team.setRacers(updatedTeam.getRacers());
            return teamRepository.save(team);
        }
        return null;
    }

    public void deleteTeam(Long id) {
        Team team = getTeamById(id);
        if (team != null) {
            teamRepository.delete(team);
        }
    }
}