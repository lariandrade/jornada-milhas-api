package com.challenge.jornadamilhasapi.dtos;

import com.challenge.jornadamilhasapi.models.Depoimento;
import jakarta.validation.constraints.NotBlank;

public record DadosDetalhamentoDepoimentoDTO(Integer id, String depoimento, String autor) {

    public DadosDetalhamentoDepoimentoDTO(Depoimento depoimento) {
        this(depoimento.getId(), depoimento.getDepoimento(), depoimento.getAutor());
    }

}
