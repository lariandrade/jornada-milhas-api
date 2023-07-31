package com.challenge.jornadamilhasapi.dtos.destino;

import java.math.BigDecimal;

public record DadosAtualizacaoDestino(
        String foto1,
        String foto2,
        String nome,
        BigDecimal preco,
        String meta,
        String textoDescritivo) {
}
