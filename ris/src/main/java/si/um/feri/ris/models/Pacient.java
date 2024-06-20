package si.um.feri.ris.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pacient")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Priimek")
    private String priimek;

    @Column(name = "Datum_rojstva")
    @Temporal(TemporalType.DATE)
    private Date datumRojstva;

    @Column(name = "Naslov")
    private String naslov;

    @Column(name = "Tel_st")
    private Integer telSt;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "ZZZS")
    private String zzzs;

    // Getters and Setters
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

    public Integer getTelSt() {
        return telSt;
    }

    public void setTelSt(Integer telSt) {
        this.telSt = telSt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZzzs() {
        return zzzs;
    }

    public void setZzzs(String zzzs) {
        this.zzzs = zzzs;
    }
}
