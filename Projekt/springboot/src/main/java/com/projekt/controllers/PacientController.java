package com.projekt.controllers;

import com.projekt.dao.*;
import com.projekt.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/pacienti")
public class PacientController {

    @Autowired
    private PacientRepository PacientRepository;
    private NapotniceRepository NapotniceRepository;

    @GetMapping
    public Iterable<Pacient> getAllPacienti() {
        return PacientRepository.findAll();
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
}
