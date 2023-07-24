package com.challenge.jornadamilhasapi.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double preco;
}
