package com.challenge.jornadamilhasapi.models;

import com.challenge.jornadamilhasapi.dtos.depoimento.DadosCadastroDepoimentoDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "depoimentos")
@Data
@NoArgsConstructor
public class Depoimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String foto;
    private String depoimento;
    private String autor;

    public Depoimento(DadosCadastroDepoimentoDTO dados) {
        this.foto = dados.foto();
        this.depoimento = dados.depoimento();
        this.autor = dados.autor();
    }
}
