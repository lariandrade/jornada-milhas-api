package com.challenge.jornadamilhasapi.dtos.destino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDestinoDTO(@NotBlank String foto, @NotBlank String nome, @NotNull Double preco) {
}
