package com.challenge.jornadamilhasapi.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DepoimentoRepositoryTest {

    @Autowired
    private DepoimentoRepository depoimentoRepository;

    @Test
    @DisplayName("Deveria devolver tres depoimentos aleatorios")
    void escolherDepoimentosAleatoriosHomeCenario01() {
        depoimentoRepository.depoimentosAleatoriosHome();
    }
}