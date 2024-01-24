package com.projekt.controllers;

import com.projekt.dao.*;
import com.projekt.models.*;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@RequestMapping("/zdravniki")
public class ZdravnikController {

    @Autowired
    private ZdravnikRepository ZdravnikRepository;
    private NapotniceRepository NapotniceRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public Iterable<Zdravnik> getAllZdravniki() {
        return ZdravnikRepository.findAll();
    }

    @GetMapping("/zdravniki/{zdravnikId}")
    public ResponseEntity<Zdravnik> getZdravnikById(@PathVariable Long zdravnikId) {
        Optional<Zdravnik> zdravnik = ZdravnikRepository.findById(zdravnikId);

        return zdravnik.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/zdravniki/register")
    public ResponseEntity<Zdravnik> registerZdravnik(@RequestBody Zdravnik zdravnik) {
        Zdravnik createdZdravnik = ZdravnikRepository.save(zdravnik);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdZdravnik);
    }


    @PutMapping("/zdravniki/{zdravnikId}")
    public ResponseEntity<Zdravnik> updateZdravnik(@PathVariable Long zdravnikId, @RequestBody Zdravnik updatedZdravnik) {
        Optional<Zdravnik> existingZdravnik = ZdravnikRepository.findById(zdravnikId);

        if (existingZdravnik.isPresent()) {
            Zdravnik zdravnikToUpdate = existingZdravnik.get();
            zdravnikToUpdate.setIme(updatedZdravnik.getIme());
            zdravnikToUpdate.setPriimek(updatedZdravnik.getPriimek());
            // Update other fields as needed

            Zdravnik updatedEntity = ZdravnikRepository.save(zdravnikToUpdate);
            return ResponseEntity.ok(updatedEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/zdravniki/{zdravnikId}")
    public ResponseEntity<Void> deleteZdravnik(@PathVariable Long zdravnikId) {
        if (ZdravnikRepository.existsById(zdravnikId)) {
            ZdravnikRepository.deleteById(zdravnikId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/zdravniki-with-appointments")
    public Iterable<Object[]> getZdravnikiWithAppointments() {
        // Use a custom query to join Zdravnik and Napotnice entities
        String queryString = "SELECT z, n FROM Zdravnik z LEFT JOIN Napotnice n ON z.id = n.zdravnik.id WHERE n.datumPregleda > CURRENT_DATE";
        Query query = entityManager.createQuery(queryString, Object[].class);

        // Execute the query and return the result
        return (Iterable<Object[]>) query.getResultList();
    }

}
