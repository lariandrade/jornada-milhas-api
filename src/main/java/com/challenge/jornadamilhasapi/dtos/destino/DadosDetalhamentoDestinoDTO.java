package com.challenge.jornadamilhasapi.dtos.destino;

import com.challenge.jornadamilhasapi.models.Destino;

import java.math.BigDecimal;

public record DadosDetalhamentoDestinoDTO(Integer id, String foto1, String foto2, String nome, BigDecimal preco,
                                          String meta, String textoDescritivo) {
    public DadosDetalhamentoDestinoDTO(Destino destino) {
        this(destino.getId(), destino.getFoto1(), destino.getFoto2(), destino.getNome(), destino.getPreco(), destino.getMeta(), destino.getTextoDescritivo());
    }
}
