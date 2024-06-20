package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import si.um.feri.ris.models.Obvestilo;

public interface ObvestiloRepository extends JpaRepository<Obvestilo, Long> {
}
