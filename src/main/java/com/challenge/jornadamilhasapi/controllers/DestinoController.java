package com.challenge.jornadamilhasapi.controllers;

import com.challenge.jornadamilhasapi.dtos.destino.DadosAtualizacaoDestino;
import com.challenge.jornadamilhasapi.dtos.destino.DadosCadastroDestinoDTO;
import com.challenge.jornadamilhasapi.dtos.destino.DadosDetalhamentoDestinoDTO;
import com.challenge.jornadamilhasapi.dtos.destino.DadosDetalhamentoDestinoPorIdDTO;
import com.challenge.jornadamilhasapi.services.DestinoService;
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

@Tag(name = "Destinos")
@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @Operation(summary = "Cadastrar", description = "será cadastrado um novo destino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Destino cadastrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDestinoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Informações inválidas", content = @Content),
    })

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoDestinoDTO> cadastrar(@RequestBody @Valid DadosCadastroDestinoDTO dados) {
        DadosDetalhamentoDestinoDTO detalhamento = destinoService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhamento);
    }

    @Operation(summary = "Listar tudo", description = "Serão listados todos os destinos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destinos listados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDestinoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum destino cadastrado", content = @Content),
    })
    @GetMapping("/todos")
    public ResponseEntity<Page<DadosDetalhamentoDestinoDTO>> listarTodos(@PageableDefault(sort = {"id"}) Pageable pageable) {
        var destinosPaginados = destinoService.findAll(pageable).map(DadosDetalhamentoDestinoDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(destinosPaginados);
    }

    @Operation(summary = "Listar por nome", description = "Serão listados os destinos referente ao nome informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destino listado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDestinoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum destino foi encontrado", content = @Content),
    })
    @GetMapping(params = "nome")
    public ResponseEntity<DadosDetalhamentoDestinoDTO> listarPorNome(@RequestParam String nome) {
        return destinoService.findByNome(nome);
    }

    @Operation(summary = "Listar por id", description = "Serão listados os destinos referente ao id informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destino listado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDestinoPorIdDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum destino foi encontrado", content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoDestinoPorIdDTO> listarPorID(@PathVariable Integer id) {
        return destinoService.findById(id);
    }

    @Operation(summary = "Atualizar", description = "Será atualizado o destino referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destino atualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDestinoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum destino cadastrado com esse ID", content = @Content),
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoDestinoDTO> atualizar(@PathVariable Integer id, @RequestBody DadosAtualizacaoDestino dados) {
        var dadosDetalhamento = destinoService.update(id, dados);
        return ResponseEntity.status(HttpStatus.OK).body(dadosDetalhamento);
    }

    @Operation(summary = "Deletar", description = "Será deletado o destino referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Destino deletado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhum destino cadastrado com esse ID", content = @Content),
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        destinoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
