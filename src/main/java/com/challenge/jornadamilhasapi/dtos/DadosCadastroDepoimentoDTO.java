package com.challenge.jornadamilhasapi.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroDepoimentoDTO(
        @NotBlank
        String foto,
        @NotBlank
        String depoimento,
        @NotBlank
        String autor) {
}
