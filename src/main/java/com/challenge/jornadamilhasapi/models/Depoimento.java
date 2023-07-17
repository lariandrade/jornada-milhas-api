package com.challenge.jornadamilhasapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "depoimentos")
public class Depoimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String foto;
    private String depoimento;
    private String autor;

}
