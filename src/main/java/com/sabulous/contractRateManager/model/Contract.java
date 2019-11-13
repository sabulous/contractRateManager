package com.sabulous.contractRateManager.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String origin;

    @NotNull
    private String destination;

    @NotNull
    private String agentName;

    @NotNull
    private String commodity;

    @NotNull
    private String weightBreak;

    @NotNull
    private String currency;

    @NotNull
    private String value;

    @NotNull
    private Date validFrom;

    @NotNull
    private Date validTo;

    @NotNull
    private String status;

    private Integer createdBy;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable
    private List<User> users = new ArrayList<>();

    private String primaryRole;

    private String secondaryRole;

    public Integer getId() {
        return id;
    }

    public String getSecondaryRole() {
        return secondaryRole;
    }

    public void setSecondaryRole(String secondaryRole) {
        this.secondaryRole = secondaryRole;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getWeightBreak() {
        return weightBreak;
    }

    public void setWeightBreak(String weightBreak) {
        this.weightBreak = weightBreak;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public void print() {
        System.out.printf("%d %s %s %s %s %s\n", id, origin, destination, agentName, validFrom.toString(), validTo.toString());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if(this.getUsers().contains(user)) {
            return;
        }
        users.add(user);
        user.addContract(this);
    }

    public void removeUser(User user) {
        if(!users.contains(user)) {
            return;
        }

        users.remove(user);

        user.removeContract(this);
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }
}