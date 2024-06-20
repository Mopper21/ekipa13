package si.um.feri.ris.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Napotnica")
public class Napotnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Pacient_ID")
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "Zdravnik_ID")
    private Zdravnik zdravnik;

    @Column(name = "Koda")
    private String koda;

    @Column(name = "Opis")
    private String opis;

    @Column(name = "Datum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;

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

    public String getKoda() {
        return koda;
    }

    public void setKoda(String koda) {
        this.koda = koda;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
