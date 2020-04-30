package com.lowes.lowesForGeeks.service;

import com.lowes.lowesForGeeks.model.Member;
import com.lowes.lowesForGeeks.model.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    ResponseEntity create(Team team, Member creator);

    ResponseEntity updateName(Team team, String name,Member updater);

    ResponseEntity addMemberExist(Team team, Member toBeAdded, Member updater );

    ResponseEntity addMemberNew(Team team, Member toBeAdded, Member updater);

    ResponseEntity removeMember(Team team, Member toBeRemoved, Member updater);

    ResponseEntity addTeamAdmin(Team team, Member admin, Member updater);

    ResponseEntity removeTeamAdmin(Team team , Member admin, Member updater);

    Iterable<Team> findAll();

    Optional<Team> findById(Integer id);

    Iterable<Team> findByTeamName(String teamName);

    ResponseEntity delete(Team team);
}
