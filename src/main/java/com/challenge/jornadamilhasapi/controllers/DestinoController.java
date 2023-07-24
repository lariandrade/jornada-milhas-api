package com.challenge.jornadamilhasapi.controllers;

import com.challenge.jornadamilhasapi.dtos.destino.DadosCadastroDestinoDTO;
import com.challenge.jornadamilhasapi.services.DestinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroDestinoDTO dados) {
        System.out.println(dados);
    }
}
