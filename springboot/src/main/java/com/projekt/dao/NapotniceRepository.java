package com.projekt.dao;

import com.projekt.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface NapotniceRepository extends CrudRepository<Napotnice, Long> {


    @Query("SELECT n FROM Napotnice n WHERE n.datumIzdaje >= CURRENT_DATE")
    List<Napotnice> findFutureNapotnice();

     Iterable<Napotnice> findByPacientId(Long pacientId);

    Iterable<Napotnice> findByDatumPregledaAfter(LocalDate currentDate);


}
