package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import si.um.feri.ris.models.Uporabnik;

public interface UporabnikRepository extends JpaRepository<Uporabnik, Long> {
    Uporabnik findByUporabniskoIme(String uporabniskoIme);
}
