package com.challenge.jornadamilhasapi.repositories;

import com.challenge.jornadamilhasapi.models.Depoimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepoimentoRepository extends JpaRepository<Depoimento, Integer> {

    @Query("""
            SELECT m 
            FROM Depoimento m
            ORDER BY RAND() LIMIT 3
            """)
    List<Depoimento> depoimentosAleatoriosHome();

}
