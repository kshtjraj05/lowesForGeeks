package com.lowes.lowesForGeeks.repository;

import com.lowes.lowesForGeeks.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Integer> {
    List<Member> findByFirstName(String firstName);
    List<Member> findByLastName(String lastName);
    List<Member> findByFirstNameAndLastName(String firstName, String LastName);
}
