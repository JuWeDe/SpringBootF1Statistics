package ru.badin.springbootf1webservice.Services;

import ru.badin.springbootf1webservice.DTO.TeamDto;

import java.util.List;
import java.util.Map;


public interface TeamService {

    TeamDto getTeamById(Long id);

    List<TeamDto> getAllTeams();

    void createTeam(TeamDto TeamDto);

    void updateTeam(Long id, TeamDto TeamDto);

    void deleteTeam(Long id);

    Map<String, Object> getAllTeamsHal(int index, int count);
}