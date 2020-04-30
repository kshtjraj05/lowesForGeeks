package com.lowes.lowesForGeeks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import static org.hibernate.annotations.CascadeType.*;

@Entity
public class Member implements Serializable {
    @Id
    @TableGenerator(name ="id" , initialValue = 2)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id")
    private Integer id;
    @NotBlank
    @Column(name = "first_name")
    @Size(min = 0, max = 100)
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    @Size(min = 0, max = 100)
    private String lastName;

    @NotBlank
    @Column(name = "email")
    @Email
    private String email;

    @JsonIgnore(value = false)
    @Column(name = "is_team_admin")
    private boolean teamAdmin;

    @JsonIgnore(value = false)
    @Column(name = "is_organization_admin")
    private boolean organizationAdmin;

    @Column(name = "team_id")
    private Integer team_id;

    @Column(name = "organization_id")
    private Integer organization_id;

    @JsonIgnore
    @ManyToMany
    private List<Event> events;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "createdBy")
    private List<Event> creator;

    @ManyToMany
    public List<Event> getEvents() {
        return events;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTeamAdmin() {
        return teamAdmin;
    }

    public void setTeamAdmin(boolean teamAdmin) {
        this.teamAdmin = teamAdmin;
    }

    public boolean isOrganizationAdmin() {
        return organizationAdmin;
    }

    public void setOrganizationAdmin(boolean organizationAdmin) {
        this.organizationAdmin = organizationAdmin;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }
}