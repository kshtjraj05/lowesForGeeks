package com.lowes.lowesForGeeks.service;

import com.lowes.lowesForGeeks.model.Member;
import com.lowes.lowesForGeeks.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MemberService  {

    Iterable<Member> findByFirstNameAndLastName(String firstName, String lastName);

    Iterable findByFirstName(String firstName);

    Iterable findByLastName(String lastName);

    Optional<Member> findById(Integer id);

    Iterable<Member> findAll();

    ResponseEntity changeFirstName(Member user, Member toBeUpdated, String firstName);

    ResponseEntity changeLastName(Member user, Member toBeUpdated, String lastName);

    ResponseEntity changeEmail(Member user, Member toBeUpdated, String email);

    ResponseEntity makeTeamAdmin(Member toBeAdmin, Member updater);

    ResponseEntity removeTeamAdmin(Member toBeNotAdmin, Member updater);

    ResponseEntity makeOrganizationAdmin(Member toBeAdmin, Member updater);

    ResponseEntity removeOrganizationAdmin(Member toBeNotAdmin, Member updater);

    ResponseEntity create(Member member, Member adder, Team team);

    ResponseEntity delete(Member member);

}
