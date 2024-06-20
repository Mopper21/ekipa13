package si.um.feri.ris.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Termin")
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Pacient_ID")
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "Zdravnik_ID")
    private Zdravnik zdravnik;

    @Column(name = "Datum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;

    @Column(name = "Status")
    private String status;

    // Getters and Setters
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
