package com.sabulous.contractRateManager.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.assertj.core.util.Lists;

@Entity
@Table(name = "USER")
public class User {

    public User() {
        this.setRoles(new ArrayList<Role>(Lists.newArrayList(new Role("USER"))));
        System.out.println("SIZE OF THE ROLE LIST upon creation of the user:" + this.getRoles().size());
        // System.out.println("is role List NULL? :" + this.getRoles() == null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    @Transient
    private String password;

    private String encryptedPassword;

    // @ManyToMany(fetch = FetchType.LAZY,
    // cascade = {
    //     CascadeType.PERSIST,
    //     CascadeType.MERGE
    // })
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "role") })
    private List<Role> roles = new ArrayList<>();



    // @JoinTable
    // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // // ~ defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id"),
    // //     inverseJoinColumns = @joinColumn(name = "role_id"))
    // private List<Role> roles = new ArrayList<>();
    
    private Integer failedLoginAttempts = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void print() {
        System.out.printf("%d %s %s %s %s %s %d\n", id, email, name, surname, password, encryptedPassword, failedLoginAttempts);
        System.out.println("ROLES from the User.java via print() method:");
        roles.forEach(x -> {x.print(); System.out.print("-");});
        System.out.println("\nEND");
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        if(!this.roles.contains(role)){
            this.roles.add(role);
        }

        if(!role.getUsers().contains(this)){
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}