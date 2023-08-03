package com.challenge.jornadamilhasapi.controllers;

import com.challenge.jornadamilhasapi.dtos.depoimento.DadosAtualizacaoDepoimento;
import com.challenge.jornadamilhasapi.dtos.depoimento.DadosCadastroDepoimentoDTO;
import com.challenge.jornadamilhasapi.dtos.depoimento.DadosDetalhamentoDepoimentoDTO;
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
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
class DepoimentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroDepoimentoDTO> dadosCadastroDepoimentoDTOJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoDepoimentoDTO> dadosDetalhamentoDepoimentoDTOJson;

    @Autowired
    private JacksonTester<DadosAtualizacaoDepoimento> dadosAtualizacaoDepoimentoJacksonTester;


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    void cadastrar_cenario01() throws Exception {
        var response = mvc.perform(post("/depoimentos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando informacoes estao validas")
    void cadastrar_cenario02() throws Exception {
        var response = mvc
                .perform(post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroDepoimentoDTOJson.write(
                                new DadosCadastroDepoimentoDTO("foto teste", "depoimento teste", "larissa")
                        ).getJson())).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 e todos depoimentos existentes")
    void listarTodos_cenario01() throws Exception {
        var response = mvc.perform(get("/depoimentos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 404 ao informar id invalido")
    void listarPorID_cenario01() throws Exception {
        var response = mvc.perform(get("/depoimentos/12")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 e listar depoimento")
    void listarPorID_cenario02() throws Exception {
        var response = mvc.perform(get("/depoimentos/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacao esta invalida")
    void atualizar_cenario01() throws Exception {
        var response = mvc.perform(post("/depoimentos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 e atualizar depoimento")
    void atualizar_cenario02() throws Exception {
        var response = mvc
                .perform(put("/depoimentos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAtualizacaoDepoimentoJacksonTester.write(
                                new DadosAtualizacaoDepoimento("depoimento teste status code")
                        ).getJson())).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 404 quando id for invalido")
    void deletar_cenario01() throws Exception {
        var response = mvc.perform(delete("/depoimentos/12")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando id for valido")
    void deletar_cenario02() throws Exception {
        var response = mvc.perform(delete("/depoimentos/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}