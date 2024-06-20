package si.um.feri.ris.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Obvestilo")
public class Obvestilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Pacient_ID")
    private Pacient pacient;

    @Column(name = "Sporočilo")
    private String sporočilo;

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

    public String getSporočilo() {
        return sporočilo;
    }

    public void setSporočilo(String sporočilo) {
        this.sporočilo = sporočilo;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
