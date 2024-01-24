package com.projekt.controllers;

import com.projekt.dao.*;
import com.projekt.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

@RestController
@RequestMapping("/napotnice")
public class NapotniceController {

    @Autowired
    private NapotniceRepository napotniceRepository;
    private PacientRepository pacientRepository;


    // Create (POST Request)
    @PostMapping
    public ResponseEntity<Napotnice> addNapotnica(@RequestBody Napotnice appointment) {
        Napotnice createdAppointment = napotniceRepository.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    // Read (GET Requests)
    @GetMapping
    public Iterable<Napotnice> getAllNapotnice() {
        return napotniceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Napotnice> getNapotnicaById(@PathVariable Long id) {
        Optional<Napotnice> napotnica = napotniceRepository.findById(id);

        return napotnica.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/future-appointments")
    public Iterable<Napotnice> getFutureAppointments() {
        LocalDate currentDate = LocalDate.now();
        return napotniceRepository.findByDatumPregledaAfter(currentDate);
    }

    @GetMapping("/by-pacient/{pacientId}")
    public Iterable<Napotnice> getAppointmentsByPacient(@PathVariable(name = "pacientId") Long pacientId) {
        return pacientRepository.findByPacientId(pacientId);
    }

    // Update (PUT Request)
    @PutMapping("/{id}")
    public ResponseEntity<Napotnice> updateNapotnica(@PathVariable Long id, @RequestBody Napotnice updatedAppointment) {
        Optional<Napotnice> existingAppointment = napotniceRepository.findById(id);

        if (existingAppointment.isPresent()) {
            Napotnice appointmentToUpdate = existingAppointment.get();
            appointmentToUpdate.setDatumIzdaje(updatedAppointment.getDatumIzdaje());
            appointmentToUpdate.setDatumPregleda(updatedAppointment.getDatumPregleda());
            // Update other fields as needed

            Napotnice updatedEntity = napotniceRepository.save(appointmentToUpdate);
            return ResponseEntity.ok(updatedEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete (DELETE Request)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNapotnica(@PathVariable Long id) {
        if (napotniceRepository.existsById(id)) {
            napotniceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
