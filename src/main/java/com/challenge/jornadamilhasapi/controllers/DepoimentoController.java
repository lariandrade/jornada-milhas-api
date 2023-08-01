package com.challenge.jornadamilhasapi.controllers;

import com.challenge.jornadamilhasapi.dtos.depoimento.DadosAtualizacaoDepoimento;
import com.challenge.jornadamilhasapi.dtos.depoimento.DadosCadastroDepoimentoDTO;
import com.challenge.jornadamilhasapi.dtos.depoimento.DadosDetalhamentoDepoimentoDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Depoimentos")
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
    @Transactional
    public ResponseEntity<DadosDetalhamentoDepoimentoDTO> cadastrar(@RequestBody @Valid DadosCadastroDepoimentoDTO dados) {
        DadosDetalhamentoDepoimentoDTO detalhamento = depoimentoService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhamento);
    }

    @Operation(summary = "Listar todos", description = "Serão listados todos os depoimentos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depoimentos listados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDepoimentoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum depoimento cadastrado", content = @Content),
    })
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoDepoimentoDTO>> listarTodos(@PageableDefault(sort = {"id"}) Pageable pageable) {
        var depoimentosPaginados = depoimentoService.findAll(pageable).map(DadosDetalhamentoDepoimentoDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(depoimentosPaginados);
    }

    @Operation(summary = "Listar um", description = "Será listado o depoimento referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depoimento listado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDepoimentoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum depoimento cadastrado com esse ID", content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoDepoimentoDTO> listarUm(@PathVariable Integer id) {
        return depoimentoService.findById(id);
    }

    @Operation(summary = "Atualizar", description = "Será atualizado o depoimento referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depoimento atualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDepoimentoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum depoimento cadastrado com esse ID", content = @Content),
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoDepoimentoDTO> atualizar(@PathVariable Integer id, @RequestBody @Valid DadosAtualizacaoDepoimento dados) {
        DadosDetalhamentoDepoimentoDTO dadosAtualizados = depoimentoService.update(id, dados);
        return ResponseEntity.status(HttpStatus.OK).body(dadosAtualizados);
    }

    @Operation(summary = "Deletar", description = "Será deletado o depoimento referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Depoimento deletado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhum depoimento cadastrado com esse ID", content = @Content),
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        depoimentoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Listar depoimentos aleatorios", description = "Serão listados 3 depoimentos aleatorios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Depoimentos listados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDepoimentoDTO.class)))

    })
    @GetMapping("/depoimentos-home")
    public ResponseEntity<List<Depoimento>> depoimentosAleatorios() {
        List<Depoimento> depoimentosHome = depoimentoService.getDepoimentosHome();
        return ResponseEntity.ok(depoimentosHome);
    }
}
