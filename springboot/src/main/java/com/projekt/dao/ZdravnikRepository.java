package com.projekt.dao;

import com.projekt.models.Zdravnik;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZdravnikRepository extends CrudRepository<Zdravnik, Long> {

    @Query("SELECT z FROM Zdravnik z WHERE z.email = ?1")
    Zdravnik findByEmail(String email);

    @Query("SELECT z FROM Zdravnik z WHERE z.ime LIKE %?1% OR z.priimek LIKE %?1%")
    List<Zdravnik> searchByName(String name);
}
