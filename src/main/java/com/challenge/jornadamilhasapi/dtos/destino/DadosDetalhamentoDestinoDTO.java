package com.challenge.jornadamilhasapi.dtos.destino;

import com.challenge.jornadamilhasapi.models.Depoimento;
import com.challenge.jornadamilhasapi.models.Destino;

import java.math.BigDecimal;

public record DadosDetalhamentoDestinoDTO(Integer id, String foto, String nome, BigDecimal preco) {
    public DadosDetalhamentoDestinoDTO(Destino destino) {
        this(destino.getId(), destino.getFoto(), destino.getNome(), destino.getPreco());
    }
}
