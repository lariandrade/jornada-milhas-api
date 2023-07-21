package com.challenge.jornadamilhasapi.controllers;

import com.challenge.jornadamilhasapi.dtos.DadosAtualizacaoDepoimento;
import com.challenge.jornadamilhasapi.dtos.DadosCadastroDepoimentoDTO;
import com.challenge.jornadamilhasapi.dtos.DadosDetalhamentoDepoimentoDTO;
import com.challenge.jornadamilhasapi.models.Depoimento;
import com.challenge.jornadamilhasapi.services.DepoimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name="Depoimentos")
@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService depoimentoService;

    @Operation(summary = "Cadastrar", description = "será cadastrado um novo depoimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                        description = "Depoimento cadastrado",
                        content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = DadosDetalhamentoDepoimentoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Informações inválidas", content = @Content),
    })
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroDepoimentoDTO dados) {
        DadosDetalhamentoDepoimentoDTO detalhamento = depoimentoService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhamento);
    }

    @Operation(summary = "Listar tudo", description = "Serão listados todos os depoimentos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depoimentos listados",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDepoimentoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum depoimento cadastrado", content = @Content),
    })
    @GetMapping
    public ResponseEntity listarTodos() {
        List<Depoimento> depoimentos = depoimentoService.findall();
        if(depoimentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum depoimento cadastrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(depoimentos);
    }

    @Operation(summary = "Listar um", description = "Será listado o depoimento referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depoimento listado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDepoimentoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum depoimento cadastrado com esse ID", content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity listarUm(@PathVariable Integer id) {
        Optional<Depoimento> depoimento = depoimentoService.findById(id);
        if(depoimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum depoimento encontrado com esse id.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(depoimento);
    }

    @Operation(summary = "Atualizar", description = "Será atualizado o depoimento referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depoimento atualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDepoimentoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum depoimento cadastrado com esse ID", content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody @Valid DadosAtualizacaoDepoimento dados) {
        ResponseEntity<String> response;
        try {
            DadosDetalhamentoDepoimentoDTO dadosDetalhamentoCadastro = depoimentoService.update(id, dados);
            return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamentoCadastro);

        } catch (NoSuchElementException e) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Depoimento não encontrado");
        }
        return response;
    }

    @Operation(summary = "Deletar", description = "Será deletado o depoimento referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depoimento deletado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhum depoimento cadastrado com esse ID", content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        Optional<Depoimento> depoimento = depoimentoService.findById(id);
        if (depoimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Depoimento não encontrado");
        }
        depoimentoService.delete(depoimento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Depoimento deletado com sucesso.");
    }

    @Operation(summary = "Listar depoimentos aleatorios", description = "Serão listados 3 depoimentos aleatorios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depoimentos listados",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosDetalhamentoDepoimentoDTO.class)))

    })
    @GetMapping("/depoimentos-home")
    public ResponseEntity depoimentosAleatorios() {
        List<Depoimento> depoimentosHome = depoimentoService.getDepoimentosHome();
        return ResponseEntity.ok(depoimentosHome);
    }
}
