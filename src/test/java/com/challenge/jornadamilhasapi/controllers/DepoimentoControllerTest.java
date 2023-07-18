package com.challenge.jornadamilhasapi.controllers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

class DepoimentoControllerTest {

    private String baseUrl = "http://localhost:8080/depoimentos";
    private Integer IdExistente = 8;
    private Integer idInexistente = 111;

    @Test
    void deveCadastrarDepoimentoComSucesso() {
        String requestBody = "{\"foto\": \"URL\", \"depoimento\": \"depoimento teste\", \"autor\": \"autor teste\"}";
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseUrl)
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void deveRetornar400AoTentarCadastrarDepoimento() {
        String requestBody = "{\"foto\": \"URL\", \"depoimento\": \"depoimento teste\", \"autor\": \"\"}";
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseUrl)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void deveListarTodosDepoimentosExistentes() {
        given()
                .when()
                .get(baseUrl)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveListarUmDepoimentoComSucesso() {
        given()
                .when()
                .get(baseUrl + "/" + IdExistente)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveRetornar404AoTentarListarDepoimentoInexistente() {
        given()
                .when()
                .get(baseUrl + "/" + idInexistente)
                .then()
                .statusCode(404);
    }

    @Test
    void deveAtualizarDepoimentoComSucesso() {
        String requestBody = "{\"depoimento\": \"A melhor plataforma!\"}";
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(baseUrl + "/" +IdExistente)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void deveRetornar400AoTentarAtualizarDepoimento() {
        String requestBody = "{\"depoimento\": \"\"}";
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(baseUrl + "/" + idInexistente)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void deveDeletarDepoimentoComSucesso() {
        given()
                .when()
                .delete(baseUrl + "/" + IdExistente)
                .then()
                .statusCode(200);
    }

    @Test
    void deveRetornar404AoTentarDeletarDepoimentoInexistente() {
        given()
                .when()
                .delete(baseUrl + "/" + idInexistente)
                .then()
                .statusCode(404);
    }

    @Test
    void deveListarTresDepoimentosAleatorios() {
        given()
                .when()
                .get(baseUrl + "/depoimentos-home")
                .then()
                .statusCode(200);
    }
}