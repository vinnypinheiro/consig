package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pais implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String sigla;

 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
