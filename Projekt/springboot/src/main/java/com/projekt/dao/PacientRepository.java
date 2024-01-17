package com.projekt.dao;

import com.projekt.models.Pacient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PacientRepository extends CrudRepository<Hisa, Long> {
    @Query("select h from Hisa h, Soba s where s.hisa = h and s.velikost >= ?1")
    List<Hisa> vrniHisePoVelikostiSob(double velikost);
}