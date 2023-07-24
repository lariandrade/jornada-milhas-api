package com.challenge.jornadamilhasapi.controllers;

import com.challenge.jornadamilhasapi.dtos.depoimento.DadosAtualizacaoDepoimento;
import com.challenge.jornadamilhasapi.dtos.depoimento.DadosDetalhamentoDepoimentoDTO;
import com.challenge.jornadamilhasapi.dtos.destino.DadosAtualizacaoDestino;
import com.challenge.jornadamilhasapi.dtos.destino.DadosCadastroDestinoDTO;
import com.challenge.jornadamilhasapi.dtos.destino.DadosDetalhamentoDestinoDTO;
import com.challenge.jornadamilhasapi.models.Depoimento;
import com.challenge.jornadamilhasapi.models.Destino;
import com.challenge.jornadamilhasapi.services.DestinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoDestinoDTO> cadastrar(@RequestBody @Valid DadosCadastroDestinoDTO dados) {
        DadosDetalhamentoDestinoDTO detalhamento = destinoService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhamento);
    }

    @GetMapping
    public ResponseEntity listarTodos(@PageableDefault(sort={"id"}) Pageable pageable) {
        Page<Destino> destinosPaginados = destinoService.findAll(pageable);
        if (destinosPaginados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum destino cadastrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(destinosPaginados);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUm(@PathVariable Integer id) {
        Optional<Destino> destino = destinoService.findById(id);
        if(destino.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum destino encontrado com esse id.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(destino);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody DadosAtualizacaoDestino dados) {
        ResponseEntity<String> response;
        try {
            DadosDetalhamentoDestinoDTO dadosDetalhamento = destinoService.update(id, dados);
            return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);

        } catch (NoSuchElementException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino não encontrado");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        Optional<Destino> destino = destinoService.findById(id);
        if (destino.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino não encontrado");
        }
        destinoService.delete(destino.get());
        return ResponseEntity.status(HttpStatus.OK).body("Destino deletado com sucesso.");
    }
}
