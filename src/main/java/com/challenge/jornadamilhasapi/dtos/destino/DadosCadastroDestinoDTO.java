package com.challenge.jornadamilhasapi.dtos.destino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DadosCadastroDestinoDTO(
        @NotBlank String foto1,
        @NotBlank String foto2,
        @NotBlank String nome,
        @NotNull BigDecimal preco,
        @NotBlank
        @Size(max = 160, message = "não pode ultrapassar 160 caracteres.")
        String meta,
        String textoDescritivo) {
}
