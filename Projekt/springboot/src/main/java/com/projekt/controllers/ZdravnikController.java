package com.projekt.controllers;

import com.projekt.dao.*;
import com.projekt.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/zdravniki")
public class ZdravnikController {

    @Autowired
    private ZdravnikRepository ZdravnikRepository;
    private NapotniceRepository NapotniceRepository;
    @GetMapping
    public Iterable<Zdravnik> getAllZdravniki() {
        return ZdravnikRepository.findAll();
    }

    @PostMapping("/register")
    public Zdravnik registerZdravnik(@RequestBody Zdravnik zdravnik) {
        return ZdravnikRepository.save(zdravnik);
    }

    @PostMapping("/{zdravnikId}/appointments")
    public ResponseEntity<Napotnice> addZdravnikAppointment(@PathVariable Long zdravnikId, @RequestBody Napotnice datumPregleda) {
        Optional<Zdravnik> optionalZdravnik = ZdravnikRepository.findById(zdravnikId);  // Assuming zdravnikRepository is autowired

        if (optionalZdravnik.isPresent()) {
            Zdravnik zdravnik = optionalZdravnik.get();
            datumPregleda.setZdravnik(zdravnik); // Set the doctor for the appointment
            Napotnice savedAppointment = NapotniceRepository.save(datumPregleda); // Assuming napotniceRepository is autowired
            return ResponseEntity.ok(savedAppointment);
        } else {
            // Handle the case where the doctor with zdravnikId is not found
            return ResponseEntity.notFound().build();
        }
    }
}
