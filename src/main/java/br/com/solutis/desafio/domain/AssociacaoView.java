package br.com.solutis.desafio.domain;


import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
public class AssociacaoView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    @Column
    private int qtdauxilios;

    @Column
    private Double totalEmprestado;


}
