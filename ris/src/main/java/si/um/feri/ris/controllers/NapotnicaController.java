package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Napotnica;
import si.um.feri.ris.repositories.NapotnicaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/napotnice")
public class NapotnicaController {

    @Autowired
    private NapotnicaRepository napotnicaRepository;

    @GetMapping
    public List<Napotnica> getAllNapotnice() {
        return napotnicaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Napotnica> getNapotnicaById(@PathVariable Long id) {
        Optional<Napotnica> napotnica = napotnicaRepository.findById(id);
        return napotnica.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Napotnica createNapotnica(@RequestBody Napotnica napotnica) {
        return napotnicaRepository.save(napotnica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Napotnica> updateNapotnica(@PathVariable Long id, @RequestBody Napotnica napotnicaDetails) {
        Optional<Napotnica> napotnica = napotnicaRepository.findById(id);
        if (napotnica.isPresent()) {
            Napotnica updatedNapotnica = napotnica.get();
            updatedNapotnica.setPacient(napotnicaDetails.getPacient());
            updatedNapotnica.setZdravnik(napotnicaDetails.getZdravnik());
            updatedNapotnica.setKoda(napotnicaDetails.getKoda());
            updatedNapotnica.setOpis(napotnicaDetails.getOpis());
            updatedNapotnica.setDatum(napotnicaDetails.getDatum());
            napotnicaRepository.save(updatedNapotnica);
            return ResponseEntity.ok(updatedNapotnica);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNapotnica(@PathVariable Long id) {
        if (napotnicaRepository.existsById(id)) {
            napotnicaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
