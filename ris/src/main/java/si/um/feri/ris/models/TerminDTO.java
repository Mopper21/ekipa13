package si.um.feri.ris.models;

public class TerminDTO {
    private Long id;
    private String datum;
    private String status;
    private Long zdravnikId;
    private String zdravnikIme;
    private String zdravnikPriimek;
    private Long pacientId;
    private String pacientIme;
    private String pacientPriimek;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getZdravnikId() {
        return zdravnikId;
    }

    public void setZdravnikId(Long zdravnikId) {
        this.zdravnikId = zdravnikId;
    }

    public String getZdravnikIme() {
        return zdravnikIme;
    }

    public void setZdravnikIme(String zdravnikIme) {
        this.zdravnikIme = zdravnikIme;
    }

    public String getZdravnikPriimek() {
        return zdravnikPriimek;
    }

    public void setZdravnikPriimek(String zdravnikPriimek) {
        this.zdravnikPriimek = zdravnikPriimek;
    }

    public Long getPacientId() {
        return pacientId;
    }

    public void setPacientId(Long pacientId) {
        this.pacientId = pacientId;
    }

    public String getPacientIme() {
        return pacientIme;
    }

    public void setPacientIme(String pacientIme) {
        this.pacientIme = pacientIme;
    }

    public String getPacientPriimek() {
        return pacientPriimek;
    }

    public void setPacientPriimek(String pacientPriimek) {
        this.pacientPriimek = pacientPriimek;
    }
}
