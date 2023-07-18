package com.challenge.jornadamilhasapi.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoDepoimento(@NotBlank String depoimento) {
}
