package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Termin;
import si.um.feri.ris.repositories.TerminRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/termini")
public class TerminController {

    @Autowired
    private TerminRepository terminRepository;

    @GetMapping
    public List<Termin> getAllTermini() {
        return terminRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Termin> getTerminById(@PathVariable Long id) {
        Optional<Termin> termin = terminRepository.findById(id);
        return termin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Termin createTermin(@RequestBody Termin termin) {
        return terminRepository.save(termin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Termin> updateTermin(@PathVariable Long id, @RequestBody Termin terminDetails) {
        Optional<Termin> termin = terminRepository.findById(id);
        if (termin.isPresent()) {
            Termin updatedTermin = termin.get();
            updatedTermin.setPacient(terminDetails.getPacient());
            updatedTermin.setZdravnik(terminDetails.getZdravnik());
            updatedTermin.setDatum(terminDetails.getDatum());
            updatedTermin.setStatus(terminDetails.getStatus());
            terminRepository.save(updatedTermin);
            return ResponseEntity.ok(updatedTermin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTermin(@PathVariable Long id) {
        if (terminRepository.existsById(id)) {
            terminRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Termin> getTerminiByZdravnikAndPacient(@RequestParam Long zdravnikId, @RequestParam Long pacientId) {
        return terminRepository.findTerminiByZdravnikAndPacient(zdravnikId, pacientId);
    }
}
