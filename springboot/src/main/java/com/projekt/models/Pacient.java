package com.projekt.models;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ime;
    private String priimek;
    private Date datumRojstva;
    private String naslov;
    private int telSt;
    private String email;

    @OneToMany(mappedBy = "pacient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Napotnice> napotnice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public Date getDatumRojstva() {
        return datumRojstva;
    }

    public void setDatumRojstva(Date datumRojstva) {
        this.datumRojstva = datumRojstva;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public int getTelSt() {
        return telSt;
    }

    public void setTelSt(int telSt) {
        this.telSt = telSt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Napotnice> getNapotnice() {
        return napotnice;
    }

    public void setNapotnice(Collection<Napotnice> napotnice) {
        this.napotnice = napotnice;
    }
}