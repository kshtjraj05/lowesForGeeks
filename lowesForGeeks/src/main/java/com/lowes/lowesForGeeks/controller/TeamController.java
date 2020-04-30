package com.lowes.lowesForGeeks.controller;

import com.google.common.collect.Iterables;
import com.lowes.lowesForGeeks.model.Member;
import com.lowes.lowesForGeeks.model.Team;
import com.lowes.lowesForGeeks.service.MemberService;
import com.lowes.lowesForGeeks.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController("teamController")
public class TeamController {

    private static final String messageNotFound =" Team not found";
    @Autowired
    private TeamService teamService;
    @Autowired
    private MemberService memberService;

    @PostMapping("lowesforgeeks/team")
    ResponseEntity<Team> create(@RequestHeader(name = "loggedInMemberId") Integer id, @Valid @RequestBody Team team)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        return teamService.create(team, memberService.findById(id).get());
    }



    @PutMapping("lowesforgeeks/team/update/changename/{name}")
    ResponseEntity<Team> updateName(@RequestHeader(name = "loggedInMemberId") Integer id, @RequestHeader(name = "teamId") Integer teamId, @PathVariable String name)
    {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user= memberService.findById(id).get();
        Optional<Team> team= teamService.findById(teamId);
        if(team.isPresent())
            return teamService.updateName(team.get(), name,  user);
        else
            throw new NoSuchElementException(messageNotFound);
    }

    @PutMapping("lowesforgeeks/team/update/add/{memberId}")
    ResponseEntity<Team> addMember(@RequestHeader(name = "loggedInMemberId") Integer id, @RequestHeader(name = "teamId") Integer teamId, @PathVariable Integer memberId )
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user= memberService.findById(id).get();
        Optional<Team> team= teamService.findById(teamId);
        if(!team.isPresent())
            throw new NoSuchElementException(messageNotFound);
        Optional<Member> member= memberService.findById(memberId);
        if(!member.isPresent())
            throw new NoSuchElementException("Member not found");
        return teamService.addMemberExist(team.get(), member.get(), user);
    }

    @PutMapping("lowesforgeeks/team/update/add")
    ResponseEntity<Team> addMember(@RequestHeader(name = "loggedInMemberId") Integer id, @RequestHeader(name = "teamId") Integer teamId, @Valid @RequestBody Member member)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user= memberService.findById(id).get();
        Optional<Team> team= teamService.findById(teamId);
        if(team.isPresent())
        return teamService.addMemberNew(team.get(), member, user);
        else
            throw new NoSuchElementException(messageNotFound);
    }

    @PutMapping("lowesforgeeks/team/update/remove/{memberId}")
    ResponseEntity<Team> removeMember(@RequestHeader(name = "loggedInMemberId") Integer id, @PathVariable Integer memberId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user= memberService.findById(id).get();
        Optional<Team> team= teamService.findById(user.getTeam_id());
        if(!team.isPresent())
            throw new NoSuchElementException(messageNotFound);
        Optional<Member> member= memberService.findById(memberId);
        if(!member.isPresent())
            throw new NoSuchElementException("Member not found");
        return teamService.removeMember(team.get(), member.get(), user);
    }
    @PutMapping("lowesforgeeks/team/update/makeTeamAdmin/{memberId}")
    ResponseEntity makeTeamAdmin(@RequestHeader(name = "loggedInMemberId")Integer id, @PathVariable Integer memberId)
    {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member updater = memberService.findById(id).get();
        Optional<Member> toBeAdmin = memberService.findById(memberId);
        if(!toBeAdmin.isPresent())
            throw new NoSuchElementException("Member not found");
        Team team = null;
        if(toBeAdmin.get().getTeam_id()!=null)
            team = teamService.findById(toBeAdmin.get().getTeam_id()).get();
        return new ResponseEntity(teamService.addTeamAdmin(team,toBeAdmin.get(), updater ), HttpStatus.OK);
    }
    @PutMapping("lowesforgeeks/team/update/removeTeamAdmin/{memberId}")
    ResponseEntity removeTeamAdmin(@RequestHeader(name = "loggedInMemberId")Integer id, @PathVariable Integer memberId)
    {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member updater = memberService.findById(id).get();
        Optional<Member> toBeNotAdmin = memberService.findById(memberId);
        if(!toBeNotAdmin.isPresent())
            throw new NoSuchElementException("Member not found");
        Team team = null;
        if(toBeNotAdmin.get().getTeam_id()!=null)
            team = teamService.findById(toBeNotAdmin.get().getTeam_id()).get();
        return new ResponseEntity(teamService.removeTeamAdmin(team,toBeNotAdmin.get(), updater), HttpStatus.OK);
    }
    @GetMapping("lowesforgeeks/team")
    Iterable<Team> readTeams(@RequestHeader(name = "loggedInMemberId") Integer id)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Iterable<Team> list= teamService.findAll();
        if(Iterables.size(list)>0)
            return list;
        else
            throw new NoSuchElementException("No teams to display");
    }

    @GetMapping("lowesforgeeks/team/{memberId}")
    Optional<Team> read(@RequestHeader(name = "loggedInMemberId") Integer id,@PathVariable Integer memberId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Optional<Team> team =  teamService.findById(memberId);
        if(team.isPresent())
            return team;
        else
            throw new NoSuchElementException(messageNotFound);
    }

    @GetMapping("lowesforgeeks/team/search")
    Iterable<Team> read(@RequestHeader(name = "loggedInMemberId") Integer id,@RequestParam(name = "name", required = true) String name)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Iterable<Team> team =  teamService.findByTeamName(name);
        if(Iterables.size(team)>0)
            return team;
        else
            throw new NoSuchElementException(messageNotFound);
    }

    @DeleteMapping("lowesforgeeks/team/{teamId}")
    ResponseEntity delete(@RequestHeader(name = "loggedInMemberId") Integer id, @PathVariable(name = "teamId") Integer teamId)
    {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
            if(user.isOrganizationAdmin() || user.isTeamAdmin() && user.getTeam_id()==teamId)
            {
                Team team = teamService.findById(teamId).get();
                if(team!=null) {
                    teamService.delete(team);
                    return new ResponseEntity("Delete Successful", HttpStatus.OK);
                }
                else
                    throw new NoSuchElementException(messageNotFound);
            }
            throw new ValidationException("Only team admins of same team or organization admins can delete a team");
    }
}
