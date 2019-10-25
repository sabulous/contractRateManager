package com.sabulous.contractRateManager.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {

    public User() {
        this.addRole(new Role("USER"));
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String name;

    private String surname;

    private String email;

    @Transient
    private String password;

    private String encryptedPassword;

    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable
    private List<Contract> contracts = new ArrayList<>();

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

    public void addRole(Role role) {
        if (!this.roles.contains(role)) {
            this.roles.add(role);
        }

        if (!role.getUsers().contains(this)) {
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        this.getRoles().forEach(role -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });
        return roles;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public void print() {
        System.out.println("USER field values:");
        System.out.printf("%d %s %s %s %s %s %s %d\n", id, email, username, name, surname, password, encryptedPassword,
                failedLoginAttempts);
        System.out.println("ROLES:");
        roles.forEach(x -> {
            x.print();
            System.out.println();
        });
    }

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

    public void addContract(Contract contract) {
        if (contracts.contains(contract)) {
            return;
        }

        contracts.add(contract);
        
        contract.addUser(this);
    }

    public void removeContract(Contract contract) {
        if(!contracts.contains(contract)) {
            return;
        }
        
        contracts.remove(contract);

        contract.getUsers().remove(this);
    }

	public Integer getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(Integer failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

}