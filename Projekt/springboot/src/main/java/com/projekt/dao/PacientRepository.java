package com.projekt.dao;

import com.projekt.models.Pacient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PacientRepository extends CrudRepository<Pacient, Long> {

    @Query("SELECT p FROM Pacient p WHERE p.email = ?1")
    Pacient findByEmail(String email);

    @Query("SELECT p FROM Pacient p WHERE p.ime LIKE %?1% OR p.priimek LIKE %?1%")
    List<Pacient> searchByName(String name);

    Iterable<Pacient> findByParameter1AndParameter2(String parameter1, String parameter2);

    Iterable<Pacient> findByImeAndDatumRojstva(String ime, String datumRojstva);


    Iterable<Pacient> findPacientByImeAndPriimekAndNaslov(String ime, String priimek, String naslov);
}
