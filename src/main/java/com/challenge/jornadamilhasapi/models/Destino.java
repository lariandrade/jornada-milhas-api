package com.challenge.jornadamilhasapi.models;

import com.challenge.jornadamilhasapi.dtos.destino.DadosAtualizacaoDestino;
import com.challenge.jornadamilhasapi.dtos.destino.DadosCadastroDestinoDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "destinos")
@Data
@NoArgsConstructor
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String foto;
    private String nome;
    private BigDecimal preco;

    public Destino(DadosCadastroDestinoDTO dados) {
        this.foto = dados.foto();
        this.nome = dados.nome();
        this.preco = dados.preco().setScale(2, RoundingMode.HALF_EVEN);
    }


    public void atualizarInformacoes(DadosAtualizacaoDestino dados) {
        if (dados.foto() != null) {
            this.foto = dados.foto();
        }

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
    }
}
