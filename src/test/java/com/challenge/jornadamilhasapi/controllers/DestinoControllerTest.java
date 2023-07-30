package com.challenge.jornadamilhasapi.controllers;

import com.challenge.jornadamilhasapi.dtos.destino.DadosAtualizacaoDestino;
import com.challenge.jornadamilhasapi.dtos.destino.DadosCadastroDestinoDTO;
import com.challenge.jornadamilhasapi.dtos.destino.DadosDetalhamentoDestinoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
class DestinoControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosCadastroDestinoDTO> dadosCadastroDestinoDTOJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoDestinoDTO> dadosDetalhamentoDestinoDTOJson;
    @Autowired
    private JacksonTester<DadosAtualizacaoDestino> dadosAtualizacaoDestinoJacksonTester;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    void cadastrar_cenario01() throws Exception {
        var response = mvc.perform(post("/destinos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estao validas")
    void cadastrar_cenario02() throws Exception {
        var response = mvc
                .perform(post("/destinos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroDestinoDTOJson.write(
                                new DadosCadastroDestinoDTO("foto teste", "nome teste", BigDecimal.valueOf(1350.00).setScale(2, RoundingMode.HALF_EVEN))
                        ).getJson())).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoDestinoDTOJson.write(
                new DadosDetalhamentoDestinoDTO(1, "foto teste", "nome teste", BigDecimal.valueOf(1350.0).setScale(2, RoundingMode.HALF_EVEN))).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 e todos destinos existentes")
    void listarTodos_cenario01() throws Exception {
        var response = mvc.perform(get("/destinos/todos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 404 ao informar nome invalido")
    void listarPorNome_cenario01() throws Exception {
        var response = mvc.perform(get("/destinos?nome=Jappao")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 e listar destinos")
    void listarPorNome_cenario02() throws Exception {
        var response = mvc.perform(get("/destinos?nome=Japao")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacao esta invalida")
    void atualizar_cenario01() throws Exception {
        var response = mvc.perform(post("/destinos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 e atualizar destinos")
    void atualizar_cenario02() throws Exception {
        var response = mvc
                .perform(put("/destinos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAtualizacaoDestinoJacksonTester.write(
                                new DadosAtualizacaoDestino("destino", "nome teste", BigDecimal.valueOf(0))
                        ).getJson())).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoDestinoDTOJson.write(
                new DadosDetalhamentoDestinoDTO(1, "destino", "nome teste", BigDecimal.valueOf(1350).setScale(2, RoundingMode.HALF_EVEN))).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo http 404 quando id for invalido")
    void deletar_cenario01() throws Exception {
        var response = mvc.perform(delete("/destinos/12")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando id for valido")
    void deletar_cenario02() throws Exception {
        var response = mvc.perform(delete("/destinos/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}