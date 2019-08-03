package com.sabulous.contractRateManager.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONTRACT")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Integer getId() {
        return id;
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
        // java.util.Date utilDate = null;
        // try {
        //     utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(validFrom);
        // } catch (ParseException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // this.validFrom = new java.sql.Date(utilDate.getTime());
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        // java.util.Date utilDate = null;
        // try {
        //     utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(validTo);
        // } catch (ParseException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // this.validTo = new java.sql.Date(utilDate.getTime());
        this.validTo = validTo;
    }

    public void print() {
        System.out.printf("%d %s %s %s %s %s\n", id, origin, destination, agentName, validFrom.toString(), validTo.toString());
    }
}