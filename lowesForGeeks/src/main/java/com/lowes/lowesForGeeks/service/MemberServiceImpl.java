package com.lowes.lowesForGeeks.service;

import com.lowes.lowesForGeeks.model.Member;
import com.lowes.lowesForGeeks.model.Organization;
import com.lowes.lowesForGeeks.model.Team;
import com.lowes.lowesForGeeks.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

    private static String message = "Member not authorized to update this member";

    @Autowired
    private MemberRepository memberReposistory;

    @Autowired

    private TeamService teamService;

    @Override
    public Iterable<Member> findByFirstNameAndLastName(String firstName, String lastName) {
        return memberReposistory.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Iterable<Member> findByFirstName(String firstName) {
        return memberReposistory.findByFirstName(firstName);
    }

    @Override
    public Iterable findByLastName(String lastName) {
        return memberReposistory.findByLastName(lastName);
    }

    @Override
    public Optional<Member> findById(Integer id) {
        System.out.println(memberReposistory.findById(id));
        return memberReposistory.findById(id);
    }

    @Override
    public Iterable<Member> findAll() {
        return memberReposistory.findAll();
    }


    @Override
    public ResponseEntity changeFirstName(Member user, Member toBeUpdated, String firstName) {
        if(user.isOrganizationAdmin() || (user.isTeamAdmin() && user.getTeam_id() == toBeUpdated.getTeam_id())|| user.getId()==toBeUpdated.getId())
        {
            toBeUpdated.setFirstName(firstName);
            return new ResponseEntity(memberReposistory.save(toBeUpdated), OK);
        }
        else
            throw new ValidationException(message);
    }

    @Override
    public ResponseEntity changeLastName(Member user, Member toBeUpdated, String lastName) {
        if(user.isOrganizationAdmin() || (user.isTeamAdmin() && user.getTeam_id() == toBeUpdated.getTeam_id()) || user.getId()==toBeUpdated.getId())
        {
            toBeUpdated.setLastName(lastName);
            return new ResponseEntity(memberReposistory.save(toBeUpdated), OK);
        }
        else
            throw new ValidationException(message);
    }

    @Override
    public ResponseEntity changeEmail(Member user, Member toBeUpdated, String email) {
        if(user.isOrganizationAdmin() || (user.isTeamAdmin() && user.getTeam_id() == toBeUpdated.getTeam_id())|| user.getId()==toBeUpdated.getId())
        {
            toBeUpdated.setEmail(email);
            return new ResponseEntity(memberReposistory.save(toBeUpdated), OK);
        }
        else
            throw new ValidationException(message);
    }

    @Override
    public ResponseEntity makeTeamAdmin(Member toBeAdmin, Member updater) {
        if(toBeAdmin.getTeam_id()==null)
            throw new ValidationException("Member has to be a team member first");
        if(!isQualified(updater,teamService.findById(toBeAdmin.getTeam_id()).get()))
            return new ResponseEntity(message, BAD_REQUEST);
        toBeAdmin.setTeamAdmin(true);
        return new ResponseEntity(memberReposistory.save(toBeAdmin), OK);
    }

    @Override
    public ResponseEntity removeTeamAdmin(Member toBeNotAdmin, Member updater) {
        if(toBeNotAdmin.getTeam_id()==null)
            throw new ValidationException("Member has to be a team member first");
        if(!isQualified(updater,teamService.findById(toBeNotAdmin.getTeam_id()).get()))
            throw new ValidationException(message);
        toBeNotAdmin.setTeamAdmin(false);
        return new ResponseEntity(memberReposistory.save(toBeNotAdmin), OK);
    }

    @Override
    public ResponseEntity makeOrganizationAdmin(Member toBeAdmin, Member updater) {
        if(updater.isOrganizationAdmin())
        {
            toBeAdmin.setOrganizationAdmin(true);
            return new ResponseEntity(memberReposistory.save(toBeAdmin), OK);
        }
        else
            throw new ValidationException(message);
    }

    @Override
    public ResponseEntity removeOrganizationAdmin(Member toBeNotAdmin, Member updater) {
        if(updater.isOrganizationAdmin())
        {
            toBeNotAdmin.setOrganizationAdmin(false);
            return new ResponseEntity(memberReposistory.save(toBeNotAdmin), OK);
        }
        else
            throw new ValidationException(message);
    }

    @Override
    public ResponseEntity create(Member member, Member adder, Team team) {

        if(team!=null) {
            if (!isQualified(adder, team))
                throw new ValidationException("Member can be added only by team admins and organizations admins.");
            Integer teamId = team.getTeamId();
            member.setTeam_id(teamId);
        }
        Integer organizationId = adder.getOrganization_id();
        member.setOrganization_id(organizationId);
        return new ResponseEntity(memberReposistory.save(member), OK);

    }



    @Override
    public ResponseEntity delete(Member member) {
        memberReposistory.delete(member);
        return new ResponseEntity("Delete Successful", OK);
    }

    private boolean isQualified(Member updater, Team team)
    {
        if((!updater.isTeamAdmin() && !updater.isOrganizationAdmin()) || (updater.isTeamAdmin() && !updater.isOrganizationAdmin() && updater.getTeam_id()!=team.getTeamId()))
            return false;
        else
            return true;
    }
}
