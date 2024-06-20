package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Obvestilo;
import si.um.feri.ris.repositories.ObvestiloRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/obvestila")
public class ObvestiloController {

    @Autowired
    private ObvestiloRepository obvestiloRepository;

    @GetMapping
    public List<Obvestilo> getAllObvestila() {
        return obvestiloRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obvestilo> getObvestiloById(@PathVariable Long id) {
        Optional<Obvestilo> obvestilo = obvestiloRepository.findById(id);
        return obvestilo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Obvestilo createObvestilo(@RequestBody Obvestilo obvestilo) {
        return obvestiloRepository.save(obvestilo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obvestilo> updateObvestilo(@PathVariable Long id, @RequestBody Obvestilo obvestiloDetails) {
        Optional<Obvestilo> obvestilo = obvestiloRepository.findById(id);
        if (obvestilo.isPresent()) {
            Obvestilo updatedObvestilo = obvestilo.get();
            updatedObvestilo.setPacient(obvestiloDetails.getPacient());
            updatedObvestilo.setSporočilo(obvestiloDetails.getSporočilo());
            updatedObvestilo.setDatum(obvestiloDetails.getDatum());
            obvestiloRepository.save(updatedObvestilo);
            return ResponseEntity.ok(updatedObvestilo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObvestilo(@PathVariable Long id) {
        if (obvestiloRepository.existsById(id)) {
            obvestiloRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
