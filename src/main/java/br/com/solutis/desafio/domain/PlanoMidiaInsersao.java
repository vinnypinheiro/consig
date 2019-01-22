package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlanoMidiaInsersao implements Serializable {
    @Id
    @GeneratedValue
    private Long id;


 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }}
