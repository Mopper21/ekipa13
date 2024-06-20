package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import si.um.feri.ris.models.Pacient;

public interface PacientRepository extends JpaRepository<Pacient, Long> {
}

