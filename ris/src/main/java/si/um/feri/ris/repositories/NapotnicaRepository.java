package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import si.um.feri.ris.models.Napotnica;

public interface NapotnicaRepository extends JpaRepository<Napotnica, Long> {
}
