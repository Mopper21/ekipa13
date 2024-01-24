package com.projekt.models;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Napotnice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "zdravnik_id", nullable = false)
    private Zdravnik zdravnik;

    private Date datumIzdaje;
    private Date datumPregleda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Zdravnik getZdravnik() {
        return zdravnik;
    }

    public void setZdravnik(Zdravnik zdravnik) {
        this.zdravnik = zdravnik;
    }

    public Date getDatumIzdaje() {
        return datumIzdaje;
    }

    public void setDatumIzdaje(Date datumIzdaje) {
        this.datumIzdaje = datumIzdaje;
    }

    public Date getDatumPregleda() {
        return datumPregleda;
    }

    public void setDatumPregleda(Date datumPregleda) {
        this.datumPregleda = datumPregleda;
    }
}
