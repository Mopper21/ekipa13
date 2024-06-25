package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Termin;
import si.um.feri.ris.models.Zdravnik;
import si.um.feri.ris.models.ZdravnikDTO;
import si.um.feri.ris.repositories.TerminRepository;
import si.um.feri.ris.repositories.ZdravnikRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zdravniki")
public class ZdravnikController {

    @Autowired
    private ZdravnikRepository zdravnikRepository;

    @GetMapping
    public List<ZdravnikDTO> getAllZdravniki() {
        List<Zdravnik> zdravniki = zdravnikRepository.findAll();
        return zdravniki.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ZdravnikDTO convertToDto(Zdravnik zdravnik) {
        ZdravnikDTO dto = new ZdravnikDTO();
        dto.setId(zdravnik.getId());
        dto.setIme(zdravnik.getIme());
        dto.setPriimek(zdravnik.getPriimek());
        dto.setSpecializacija(zdravnik.getSpecializacija());
        dto.setTelefon(zdravnik.getTelefon());
        dto.setEmail(zdravnik.getEmail());
        return dto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZdravnikDTO> getZdravnikById(@PathVariable Long id) {
        Optional<Zdravnik> zdravnik = zdravnikRepository.findById(id);
        return zdravnik.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Zdravnik createZdravnik(@RequestBody Zdravnik zdravnik) {
        return zdravnikRepository.save(zdravnik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZdravnikDTO> updateZdravnik(@PathVariable Long id, @RequestBody Zdravnik zdravnikDetails) {
        Optional<Zdravnik> zdravnik = zdravnikRepository.findById(id);
        if (zdravnik.isPresent()) {
            Zdravnik updatedZdravnik = zdravnik.get();
            updatedZdravnik.setIme(zdravnikDetails.getIme());
            updatedZdravnik.setPriimek(zdravnikDetails.getPriimek());
            updatedZdravnik.setSpecializacija(zdravnikDetails.getSpecializacija());
            updatedZdravnik.setTelefon(zdravnikDetails.getTelefon());
            updatedZdravnik.setEmail(zdravnikDetails.getEmail());
            zdravnikRepository.save(updatedZdravnik);
            return ResponseEntity.ok(convertToDto(updatedZdravnik));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZdravnik(@PathVariable Long id) {
        if (zdravnikRepository.existsById(id)) {
            zdravnikRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private TerminRepository terminRepository;

    @GetMapping("/{id}/termin")
    public ResponseEntity<List<Termin>> getZdravnikTermini(@PathVariable Long id) {
        List<Termin> termini = terminRepository.findByZdravnikId(id);
        return ResponseEntity.ok(termini);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllZdravniki() {
        zdravnikRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
