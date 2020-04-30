package com.lowes.lowesForGeeks.repository;

import com.lowes.lowesForGeeks.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRespository extends CrudRepository<Team, Integer>{
    List<Team> findByTeamName(String teamName);


}

