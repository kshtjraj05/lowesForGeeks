package com.lowes.lowesForGeeks.service;

import com.lowes.lowesForGeeks.model.Member;
import com.lowes.lowesForGeeks.model.Team;
import com.lowes.lowesForGeeks.repository.TeamRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service("teamSrevice")
public class TeamServiceImpl implements TeamService {

    private static String message="Updation can only be done by team admins of same team or organization admins";

    @Autowired
    private TeamRespository teamRespository;

    @Autowired

    private MemberService memberService;

    @Override
    public Iterable<Team> findByTeamName(String teamName)
    {
        return teamRespository.findByTeamName(teamName);
    }

    @Override
    public Iterable<Team> findAll()
    {
        return teamRespository.findAll();
    }

    @Override
    public Optional<Team> findById(Integer id)
    {
        return teamRespository.findById(id);
    }


    @Override
    public ResponseEntity create(Team team, Member creator) {

        if(!creator.isOrganizationAdmin())
            throw new ValidationException("Requesting member is not an Organization admin. So cannot create a new Team.");
        List<Member> members=team.getMembers();
        members.add(creator);
        team.setMembers(members);
        //creator.setTeam_id(team.getTeamId());
        creator.setTeamAdmin(true);
        return new ResponseEntity(teamRespository.save(team), HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateName(Team team, String name, Member updater)
    {
        if(!isQualified(updater,team))
            throw new ValidationException(message);
        team.setTeamName(name);
        return new ResponseEntity(teamRespository.save(team), HttpStatus.OK);
    }

    @Override
    public ResponseEntity addMemberExist(Team team, Member toBeAdded, Member updater)
    {
        if(!isQualified(updater, team))
            throw new ValidationException(message);
        if(toBeAdded.getTeam_id() != null) {
            throw  new ValidationException("Member already part of a team");
        }
        List<Member> members=team.getMembers();
        members.add(toBeAdded);
        return new ResponseEntity(teamRespository.save(team), HttpStatus.OK);
    }

    @Override
    public ResponseEntity addMemberNew(Team team, Member toBeAdded, Member updater) {
        if(!isQualified(updater, team))
            throw new ValidationException(message);
        ResponseEntity temp=  memberService.create(toBeAdded, updater , team);
        return new ResponseEntity(teamRespository.save(team), HttpStatus.OK);
    }

    @Override
    public ResponseEntity removeMember(Team team, Member toBeRemoved, Member updater)
    {
        if(!isQualified(updater, team))
            throw new ValidationException(message);
        ResponseEntity temp = memberService.delete(toBeRemoved);

        return new ResponseEntity(teamRespository.save(team), HttpStatus.OK);
    }

    @Override
    public ResponseEntity addTeamAdmin(Team team, Member toBeAdmin, Member updater) {
        if(!isQualified(updater, team))
            throw new ValidationException(message);
        memberService.makeTeamAdmin(toBeAdmin, updater);
        //List<Member> admins = team.getTeamAdmins();
        //admins.add(admin);
        //team.setTeamAdmins(admins);
        //admin.setTeamAdmin(true);
        return new ResponseEntity(teamRespository.save(team), HttpStatus.OK);
    }

    @Override
    public ResponseEntity removeTeamAdmin(Team team, Member toBeNotAdmin, Member updater) {
        if(!isQualified(updater, team))
            throw new ValidationException(message);
        memberService.removeTeamAdmin(toBeNotAdmin, updater);
        //List<Member> admins = team.getTeamAdmins();
        //admins.remove(admin);
        //admin.setTeamAdmin(false);
        return new ResponseEntity(teamRespository.save(team), HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(Team team) {
        teamRespository.delete(team);
        return new ResponseEntity("Delete Successful", HttpStatus.OK);
    }

    private boolean isQualified(Member updater, Team team)
    {
        if((!updater.isTeamAdmin() && !updater.isOrganizationAdmin()) || (updater.isTeamAdmin() && !updater.isOrganizationAdmin() && updater.getTeam_id()!=team.getTeamId()))
            return false;
        else
            return true;
    }
}

