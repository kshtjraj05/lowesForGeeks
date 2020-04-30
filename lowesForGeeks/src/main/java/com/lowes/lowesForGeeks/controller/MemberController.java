package com.lowes.lowesForGeeks.controller;

import com.google.common.collect.Iterables;
import com.lowes.lowesForGeeks.model.Member;
import com.lowes.lowesForGeeks.model.Team;
import com.lowes.lowesForGeeks.service.MemberService;
import com.lowes.lowesForGeeks.service.TeamService;
import com.lowes.lowesForGeeks.util.ErrorMessage;
import javassist.NotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController("memberController")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/lowesforgeeks/member")
    Iterable<Member> readMembers(@RequestHeader(name = "loggedInMemberId") Integer id)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");
        return memberService.findAll();
    }

    @GetMapping("/lowesforgeeks/member/{memberId}")
    Optional<Member> readMembers(@RequestHeader(name = "loggedInMemberId") Integer id,@PathVariable Integer memberId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");
        Optional<Member> member = memberService.findById(memberId);
        if(member.isPresent())
            return member;
        else
            throw new NoSuchElementException("Member not found");
    }


    @GetMapping("/lowesforgeeks/member/search")
    Iterable<Member> readMembers(@RequestHeader(name = "loggedInMemberId") Integer id,@RequestParam( name = "first", required = false) String firstName, @RequestParam(name = "last", required = false) String lastName)  {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");
        Iterable<Member> members = null;
        if(firstName!=null&&lastName!=null)
            members = memberService.findByFirstNameAndLastName(firstName, lastName);

        else if(firstName!=null)
            members= memberService.findByFirstName(firstName);
        else if(lastName!=null)
            members =  memberService.findByLastName(lastName);
        if(Iterables.size(members)>0)
            return members;
        else
            throw new NoSuchElementException("Member not found");
    }

    @PostMapping("lowesforgeeks/member")
    ResponseEntity<Member> create(@RequestHeader( name="loggedInMemberId") Integer id, @Nullable  @RequestHeader(name = "teamId") Integer teamId, @Valid  @RequestBody Member member)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member adder = memberService.findById(id).get();
        Team team=null;
        if(teamId!=null)
            team = teamService.findById(teamId).get();
        return memberService.create(member,adder,team);
    }



    @PutMapping("lowesforgeeks/member/update/changeFirstName/{firstName}")
    ResponseEntity<Member> changeFirstName(@RequestHeader(name = "loggedInMemberId") Integer id, @RequestHeader(name = "memberId") Integer memberId,@PathVariable String firstName) {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");


        Member user = memberService.findById(id).get();
        Optional<Member> toBeUpdated = memberService.findById(memberId);
        if(toBeUpdated.isPresent())
            return memberService.changeFirstName(user, toBeUpdated.get(),firstName);
        else
            throw new NoSuchElementException("Member not found");
    }

    @PutMapping("lowesforgeeks/member/update/changeLastName/{lastName}")
    ResponseEntity<Member> changeLasttName(@RequestHeader(name = "loggedInMemberId") Integer id, @RequestHeader(name = "memberId") Integer memberId,@PathVariable String lastName) {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        Optional<Member> toBeUpdated = memberService.findById(memberId);
        if(toBeUpdated.isPresent())
            return memberService.changeLastName(user, toBeUpdated.get(),lastName);
        else
            throw new NoSuchElementException("Member not found");
    }

    @PutMapping("lowesforgeeks/member/update/changeEmail/{email}")
    ResponseEntity<Member> changeEmail(@RequestHeader(name = "loggedInMemberId") Integer id, @RequestHeader(name = "memberId") Integer memberId,@PathVariable String email) {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        Optional<Member> toBeUpdated = memberService.findById(memberId);
        if(toBeUpdated.isPresent())
            return memberService.changeEmail(user, toBeUpdated.get(),email);
        else
            throw new NoSuchElementException("Member not found");
    }

    @PutMapping("lowesforgeeks/member/update/makeTeamAdmin/{memberId}")
    ResponseEntity makeTeamAdmin(@RequestHeader(name = "loggedInMemberId")Integer id, @PathVariable Integer memberId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member updater = memberService.findById(id).get();
        Optional<Member> toBeAdmin = memberService.findById(memberId);
        if(toBeAdmin.isPresent())
            return new ResponseEntity(memberService.makeTeamAdmin(toBeAdmin.get(), updater), HttpStatus.OK);
        else
            throw new NoSuchElementException("Member not found");
    }
    @PutMapping("lowesforgeeks/member/update/removeTeamAdmin/{memberId}")
    ResponseEntity removeTeamAdmin(@RequestHeader(name = "loggedInMemberId")Integer id, @PathVariable Integer memberId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member updater = memberService.findById(id).get();
        Optional<Member> toBeNotAdmin = memberService.findById(memberId);
        if(toBeNotAdmin.isPresent())
            return new ResponseEntity(memberService.removeTeamAdmin(toBeNotAdmin.get(), updater), HttpStatus.OK);
        else
            throw new NoSuchElementException("Member not found");
    }
    @PutMapping("lowesforgeeks/member/update/makeOrganizationAdmin/{memberId}")
    ResponseEntity makeOrganizationAdmin(@RequestHeader(name = "loggedInMemberId")Integer id, @PathVariable Integer memberId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member updater = memberService.findById(id).get();
        Optional<Member> toBeAdmin = memberService.findById(memberId);
        if(toBeAdmin.isPresent())
            return new ResponseEntity(memberService.makeOrganizationAdmin(toBeAdmin.get(), updater), HttpStatus.OK);
        else
            throw new NoSuchElementException("Member not found");
    }
    @PutMapping("lowesforgeeks/member/update/removeOrganizationAdmin/{memberId}")
    ResponseEntity removeOrganizationAdmin(@RequestHeader(name = "loggedInMemberId")Integer id, @PathVariable Integer memberId)
    {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member updater = memberService.findById(id).get();
        Optional<Member> toBeNotAdmin = memberService.findById(memberId);
        if(toBeNotAdmin.isPresent())
            return new ResponseEntity(memberService.removeOrganizationAdmin(toBeNotAdmin.get(), updater), HttpStatus.OK);
        else
            throw new NoSuchElementException("Member not found");
    }

    @DeleteMapping("lowesforgeeks/member")
    ResponseEntity delete(@RequestHeader(name = "loggedInMemberId") Integer id)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        return memberService.delete(memberService.findById(id).get());
    }
}