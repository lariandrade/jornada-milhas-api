package com.challenge.jornadamilhasapi.dtos.destino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroDestinoDTO(@NotBlank String foto, @NotBlank String nome, @NotNull BigDecimal preco) {
}
