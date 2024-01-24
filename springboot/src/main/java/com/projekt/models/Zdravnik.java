package com.projekt.models;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Zdravnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ime;
    private String priimek;
    private String specializacija;
    private int telefon;
    private String email;
    private String zdravnikcol;

    @OneToMany(mappedBy = "zdravnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public String getSpecializacija() {
        return specializacija;
    }

    public void setSpecializacija(String specializacija) {
        this.specializacija = specializacija;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZdravnikcol() {
        return zdravnikcol;
    }

    public void setZdravnikcol(String zdravnikcol) {
        this.zdravnikcol = zdravnikcol;
    }

    public Collection<Napotnice> getNapotnice() {
        return napotnice;
    }

    public void setNapotnice(Collection<Napotnice> napotnice) {
        this.napotnice = napotnice;
    }
}
