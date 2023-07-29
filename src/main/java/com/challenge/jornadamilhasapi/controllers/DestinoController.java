package com.challenge.jornadamilhasapi.controllers;

import com.challenge.jornadamilhasapi.dtos.destino.DadosAtualizacaoDestino;
import com.challenge.jornadamilhasapi.dtos.destino.DadosCadastroDestinoDTO;
import com.challenge.jornadamilhasapi.dtos.destino.DadosDetalhamentoDestinoDTO;
import com.challenge.jornadamilhasapi.models.Destino;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name="Destinos")
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
    public ResponseEntity listarTodos(@PageableDefault(sort={"id"}) Pageable pageable) {
        Page<Destino> destinosPaginados = destinoService.findAll(pageable);
        if (destinosPaginados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum destino cadastrado.");
        }
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
    public ResponseEntity listarPorNome(@RequestParam String nome) {
        List<Destino> destino = destinoService.findByNome(nome);
        if(destino.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum destino foi encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(destino);
    }

    @Operation(summary = "Atualizar", description = "Será atualizado o destino referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destino atualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhamentoDestinoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum destino cadastrado com esse ID", content = @Content),
    })
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

    @Operation(summary = "Deletar", description = "Será deletado o destino referente ao ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destino deletado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhum destino cadastrado com esse ID", content = @Content),
    })
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
