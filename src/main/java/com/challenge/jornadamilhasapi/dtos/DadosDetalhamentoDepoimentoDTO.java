package com.challenge.jornadamilhasapi.dtos;

import com.challenge.jornadamilhasapi.models.Depoimento;

public record DadosDetalhamentoDepoimentoDTO(Integer id, String depoimento, String autor) {

    public DadosDetalhamentoDepoimentoDTO(Depoimento depoimento) {
        this(depoimento.getId(), depoimento.getDepoimento(), depoimento.getAutor());
    }

}
