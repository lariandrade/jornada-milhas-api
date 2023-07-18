package com.challenge.jornadamilhasapi.controllers;

import com.challenge.jornadamilhasapi.dtos.DadosAtualizacaoDepoimento;
import com.challenge.jornadamilhasapi.dtos.DadosCadastroDepoimentoDTO;
import com.challenge.jornadamilhasapi.dtos.DadosDetalhamentoDepoimentoDTO;
import com.challenge.jornadamilhasapi.models.Depoimento;
import com.challenge.jornadamilhasapi.services.DepoimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService depoimentoService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroDepoimentoDTO dados) {
        DadosDetalhamentoDepoimentoDTO detalhamento = depoimentoService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhamento);
    }

    @GetMapping
    public ResponseEntity listarTodos() {
        List<Depoimento> depoimentos = depoimentoService.findall();
        if(depoimentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum depoimento cadastrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(depoimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUm(@PathVariable Integer id) {
        Optional<Depoimento> depoimento = depoimentoService.findById(id);
        if(depoimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum depoimento encontrado com esse id.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(depoimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody DadosAtualizacaoDepoimento dados) {
        ResponseEntity<String> response;
        try {
            DadosDetalhamentoDepoimentoDTO dadosDetalhamentoCadastro = depoimentoService.update(id, dados);
            return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamentoCadastro);

        } catch (NoSuchElementException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Depoimento não encontrado");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        Optional<Depoimento> depoimento = depoimentoService.findById(id);
        if (depoimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Depoimento não encontrado");
        }
        depoimentoService.delete(depoimento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Depoimento deletado com sucesso.");
    }
}
