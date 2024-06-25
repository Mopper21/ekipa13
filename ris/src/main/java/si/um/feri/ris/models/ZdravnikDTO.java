package si.um.feri.ris.models;

public class ZdravnikDTO {
    private Long id;
    private String ime;
    private String priimek;
    private String specializacija;
    private Integer telefon;
    private String email;

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
}
