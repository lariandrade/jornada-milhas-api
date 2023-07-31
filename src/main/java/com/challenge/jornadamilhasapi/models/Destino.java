package com.challenge.jornadamilhasapi.models;

import com.challenge.jornadamilhasapi.dtos.destino.DadosAtualizacaoDestino;
import com.challenge.jornadamilhasapi.dtos.destino.DadosCadastroDestinoDTO;
import jakarta.persistence.*;
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
    private String foto1;
    private String foto2;
    private String nome;
    private BigDecimal preco;
    private String meta;

    @Column(name = "texto_descritivo")
    private String textoDescritivo;

    public Destino(DadosCadastroDestinoDTO dados) {
        this.foto1 = dados.foto1();
        this.foto2 = dados.foto2();
        this.nome = dados.nome();
        this.preco = dados.preco().setScale(2, RoundingMode.HALF_EVEN);
        this.meta = dados.meta();
        this.textoDescritivo = dados.textoDescritivo();
    }

    public void atualizarInformacoes(DadosAtualizacaoDestino dados) {
        if (dados.foto1() != null) {
            this.foto1 = dados.foto1();
        }
        if (dados.foto2() != null) {
            this.foto2 = dados.foto2();
        }
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.preco() != null && dados.preco() != BigDecimal.ZERO) {
            this.preco = dados.preco();
        }
        if (dados.meta() != null) {
            this.meta = dados.meta();
        }
        if (dados.textoDescritivo() != null) {
            this.textoDescritivo = dados.textoDescritivo();
        }
    }
}
