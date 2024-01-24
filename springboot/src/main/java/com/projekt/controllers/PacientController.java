package com.projekt.controllers;

import com.projekt.dao.*;
import com.projekt.models.*;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/pacienti")
public class PacientController {

    @Autowired
    private PacientRepository PacientRepository;
    private NapotniceRepository NapotniceRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public Iterable<Pacient> getAllPacienti() {
        return PacientRepository.findAll();
    }

    @GetMapping("/{pacientId}")
    public Optional<Pacient> getPacientById(@PathVariable Long pacientId) {
        return PacientRepository.findById(pacientId);
    }


    @PostMapping("/register")
    public Pacient registerPacient(@RequestBody Pacient pacient) {
        return PacientRepository.save(pacient);
    }


    @GetMapping("/{pacientId}/appointments")
    public Iterable<Napotnice> getPacientAppointments(@PathVariable Long pacientId) {
        // Assuming you have a method in PacientRepository to get appointments by pacientId
        Pacient pacient = PacientRepository.findById(pacientId).orElse(null);
        if (pacient != null) {
            return pacient.getNapotnice();  // Adjust the method name based on your Pacient entity
        } else {
            // Handle the case where pacientId is not found
            return Collections.emptyList();  // or return null, throw an exception, etc.
        }
    }

    @PostMapping("/{pacientId}/appointments")
    public Napotnice bookAppointment(@PathVariable Long pacientId, @RequestBody Napotnice appointment) {
        Pacient pacient = PacientRepository.findById(pacientId).orElse(null);
        if (pacient != null) {
            appointment.setPacient(pacient);  // Set the pacient for the appointment
            return NapotniceRepository.save(appointment);  // Assuming napotniceRepository is autowired
        } else {
            // Handle the case where pacientId is not found
            return null;  // or throw an exception, return an error response, etc.
        }
    }

    @PutMapping("/{pacientId}")
    public ResponseEntity<Pacient> updatePacient(@PathVariable Long pacientId, @RequestBody Pacient updatedPacient) {
        // Assume PacientRepository has a method to find by ID
        Pacient existingPacient = PacientRepository.findById(pacientId).orElse(null);

        if (existingPacient != null) {
            // Update fields of existingPacient with fields from updatedPacient
            existingPacient.setIme(updatedPacient.getIme());
            existingPacient.setDatumRojstva(updatedPacient.getDatumRojstva());

            // Save the updated pacient
            Pacient updatedPacientEntity = PacientRepository.save(existingPacient);

            return ResponseEntity.ok(updatedPacientEntity);
        } else {
            // If pacient with given ID is not found, return 404 (Not Found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{pacientId}")
    public ResponseEntity<Void> deletePacient(@PathVariable Long pacientId) {
        // Assume PacientRepository has a method to find by ID
        Pacient existingPacient = PacientRepository.findById(pacientId).orElse(null);

        if (existingPacient != null) {
            // Delete the pacient
            PacientRepository.deleteById(pacientId);

            return ResponseEntity.noContent().build();
        } else {
            // If pacient with given ID is not found, return 404 (Not Found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/findByImeAndDatumRojstva")
    public Iterable<Pacient> findByImeAndDatumRojstva(
            @RequestParam String ime,
            @RequestParam String datumRojstva) {
        return PacientRepository.findByImeAndDatumRojstva(ime, datumRojstva);
    }

    @GetMapping("/byImeAndPriimekAndNaslov")
    public ResponseEntity<Iterable<Pacient>> findPacientByImeAndPriimekAndNaslov(
            @RequestParam String ime,
            @RequestParam String priimek,
            @RequestParam String naslov) {
        Iterable<Pacient> pacients = PacientRepository.findPacientByImeAndPriimekAndNaslov(ime, priimek, naslov);
        return ResponseEntity.ok(pacients);
    }

    @GetMapping("/pacients-with-appointments/{zdravnikId}")
    public Iterable<Object[]> getPacientsWithAppointmentsForZdravnik(@PathVariable Long zdravnikId) {
        // Use a custom query to join Pacient and Napotnice entities for a specific Zdravnik
        String queryString = "SELECT p, n FROM Pacient p JOIN p.napotnice n WHERE n.zdravnik.id = :zdravnikId AND n.datumPregleda > CURRENT_DATE";
        Query query = entityManager.createQuery(queryString, Object[].class);
        query.setParameter("zdravnikId", zdravnikId);

        // Execute the query and return the result
        return (Iterable<Object[]>) query.getResultList();
    }


}

