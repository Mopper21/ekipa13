package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Pacient;
import si.um.feri.ris.repositories.PacientRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacienti")
public class PacientController {

    @Autowired
    private PacientRepository pacientRepository;

    @GetMapping
    public List<Pacient> getAllPacienti() {
        return pacientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pacient> getPacientById(@PathVariable Long id) {
        Optional<Pacient> pacient = pacientRepository.findById(id);
        return pacient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pacient createPacient(@RequestBody Pacient pacient) {
        return pacientRepository.save(pacient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacient> updatePacient(@PathVariable Long id, @RequestBody Pacient pacientDetails) {
        Optional<Pacient> pacient = pacientRepository.findById(id);
        if (pacient.isPresent()) {
            Pacient updatedPacient = pacient.get();
            updatedPacient.setIme(pacientDetails.getIme());
            updatedPacient.setPriimek(pacientDetails.getPriimek());
            updatedPacient.setDatumRojstva(pacientDetails.getDatumRojstva());
            updatedPacient.setNaslov(pacientDetails.getNaslov());
            updatedPacient.setTelSt(pacientDetails.getTelSt());
            updatedPacient.setEmail(pacientDetails.getEmail());
            updatedPacient.setZzzs(pacientDetails.getZzzs());
            pacientRepository.save(updatedPacient);
            return ResponseEntity.ok(updatedPacient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacient(@PathVariable Long id) {
        if (pacientRepository.existsById(id)) {
            pacientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
