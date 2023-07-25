package com.challenge.jornadamilhasapi.repositories;

import com.challenge.jornadamilhasapi.models.Destino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinoRepository extends JpaRepository<Destino, Integer> {
    List<Destino> findByNome(String nome);
}
