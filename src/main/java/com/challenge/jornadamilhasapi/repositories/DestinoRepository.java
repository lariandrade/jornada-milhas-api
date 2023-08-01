package com.challenge.jornadamilhasapi.repositories;

import com.challenge.jornadamilhasapi.models.Destino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DestinoRepository extends JpaRepository<Destino, Integer> {
    Optional<Destino> findByNome(String nome);
}
