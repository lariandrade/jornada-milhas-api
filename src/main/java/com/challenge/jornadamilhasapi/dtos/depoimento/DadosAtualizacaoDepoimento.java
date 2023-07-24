package com.challenge.jornadamilhasapi.dtos.depoimento;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoDepoimento(@NotBlank String depoimento) {
}
