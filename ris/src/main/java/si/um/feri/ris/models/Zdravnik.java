package si.um.feri.ris.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Zdravnik")
public class Zdravnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Priimek")
    private String priimek;

    @Column(name = "Specializacija")
    private String specializacija;

    @Column(name = "Telefon")
    private Integer telefon;

    @Column(name = "Email")
    private String email;

    @OneToOne
    @JoinColumn(name = "uporabnik_id")
    private Uporabnik uporabnik;

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

    public String getSpecializacija() {
        return specializacija;
    }

    public void setSpecializacija(String specializacija) {
        this.specializacija = specializacija;
    }

    public Integer getTelefon() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }
}
