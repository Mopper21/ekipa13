package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import si.um.feri.ris.models.Termin;

import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Long> {
    List<Termin> findByZdravnikId(Long zdravnikId);
    List<Termin> findByZdravnikIdAndStatus(Long zdravnikId, String status);
    List<Termin> findByStatus(String status);
}
