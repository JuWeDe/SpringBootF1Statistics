package ru.badin.springbootf1webservice.Services;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.badin.springbootf1webservice.DTO.TeamDto;
import ru.badin.springbootf1webservice.HAL.HAL;
import ru.badin.springbootf1webservice.model.Team;
import ru.badin.springbootf1webservice.repostory.TeamRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TeamDto getTeamById(Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        return teamOptional.map(this::mapToDTO).orElse(null);

    }

    @Override
    public List<TeamDto> getAllTeams() {
        List<Team> teamList = teamRepository.findAll();
        return teamList.stream().map(this::mapToDTO).collect(Collectors.toList());

    }

    @Override
    public void createTeam(TeamDto teamDto) {
        Team team = mapToEntity(teamDto);
        teamRepository.save(team);
    }

    @Override
    public void updateTeam(Long id, TeamDto teamDto) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isPresent()) {
            Team team = mapToEntity(teamDto);
            team.setId(id);
            teamRepository.save(team);
        } else {
            throw new RuntimeException("Team not found: " + id);
        }
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);

    }

    private TeamDto mapToDTO(Team team) {
        return modelMapper.map(team, TeamDto.class);
    }

    private Team mapToEntity(TeamDto teamDto) {
        return modelMapper.map(teamDto, Team.class);
    }

    @Override
    public Map<String, Object> getAllTeamsHal(int index, int count) {
        Page<TeamDto> teamPage = getAllTeams(index, count);
        String baseUrl = "/api/teams";
        int pageNumber = teamPage.getNumber();
        int pageSize = teamPage.getSize();
        int total = teamPage.getTotalPages();

        List<Map<String, Object>> embeddedteams = teamPage.getContent().stream()
                .map(this::createTeamResource)
                .toList();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("_embedded", Collections.singletonMap("teams", embeddedteams));
        response.put("_links", HAL.paginateAsDictionary(baseUrl, pageNumber, pageSize, total));

        return response;
    }

    private Map<String, Object> createTeamResource(TeamDto teamDto) {
        Map<String, Object> teamResource = new LinkedHashMap<>();
        teamResource.put("_links", createTeamLinks(teamDto));
        teamResource.put("id", teamDto.getId());
        teamResource.put("name", teamDto.getName());
        teamResource.put("points", teamDto.getPoints());
        teamResource.put("teamPrinciple", teamDto.getTeamPrinciple());
        return teamResource;
    }

    private Map<String, Object> createTeamLinks(TeamDto teamDto) {
        String baseUrl = "/api/teams";
        Long teamId = teamDto.getId();
        Map<String, Object> links = new LinkedHashMap<>();
        links.put("self", Collections.singletonMap("href", baseUrl + "/" + teamId));
        return links;
    }

    public Page<TeamDto> getAllTeams(int index, int count) {
        PageRequest pageable = PageRequest.of(index, count);
        Page<Team> teamPage = teamRepository.findAll(pageable);
        List<TeamDto> teamDtos = teamPage.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(teamDtos, pageable, teamPage.getTotalElements());
    }

    public Team createTeam(String name, String teamPrinciple, float points) {
        Team team = new Team();
        team.setTeamPrinciple(teamPrinciple);
        team.setName(name);
        team.setPoints(points);
        return teamRepository.save(team);

    }

//    @Override
//    Hateoas realization
//    public Map<String, Object> getAllTeamsHal(int index, int count) {
//        List<Team> teamList = teamRepository.findAll(PageRequest.of(index, count)).getContent();
//        List<TeamDto> teamDtos = teamList.stream().map(this::mapToDTO).collect(Collectors.toList());
//
//        Map<String, Object> halResponse = new LinkedHashMap<>();
//        halResponse.put("_embedded", HAL.addEmbeddedResource("teams", teamDtos));
//        halResponse.put("_links", HAL.paginateAsDictionary("/teams", index, count, teamRepository.findAll().size()));
//        return halResponse;
//    }
}
