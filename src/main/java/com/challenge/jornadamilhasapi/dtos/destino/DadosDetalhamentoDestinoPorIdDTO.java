package com.challenge.jornadamilhasapi.dtos.destino;

import com.challenge.jornadamilhasapi.models.Destino;

public record DadosDetalhamentoDestinoPorIdDTO(String foto1, String foto2, String nome, String meta, String textoDescritivo) {
    public DadosDetalhamentoDestinoPorIdDTO(Destino destino) {
        this(destino.getFoto1(), destino.getFoto2(), destino.getNome(), destino.getMeta(), destino.getTextoDescritivo());
    }
}
