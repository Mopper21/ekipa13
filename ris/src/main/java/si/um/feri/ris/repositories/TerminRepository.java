package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import si.um.feri.ris.models.Termin;

import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Long> {

    @Query("SELECT t FROM Termin t WHERE t.zdravnik.id = :zdravnikId AND t.pacient.id = :pacientId")
    List<Termin> findTerminiByZdravnikAndPacient(Long zdravnikId, Long pacientId);
}
