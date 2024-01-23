package com.projekt.controllers;

import com.projekt.dao.*;
import com.projekt.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;

@RestController
@RequestMapping("/napotnice")
public class NapotniceController {

    @Autowired
    private NapotniceRepository NapotniceRepository;

    @GetMapping
    public Iterable<Napotnice> getAllNapotnice() {
        return NapotniceRepository.findAll();
    }

    @PostMapping
    public Napotnice addNapotnica(@RequestBody Napotnice appointment) {
        return NapotniceRepository.save(appointment);
    }
    @GetMapping("/future-appointments")
    public Iterable<Napotnice> getFutureAppointments() {
        LocalDate currentDate = LocalDate.now();
        return NapotniceRepository.findByDatumPregledaAfter(currentDate);
    }

    @GetMapping("/by-pacient/{pacientId}")
    public Long getAppointmentsByPacient(@PathVariable(name = "pacientId") Long pacientId) {
        return com.projekt.dao.NapotniceRepository.findByPacientId(pacientId);
    }
}
