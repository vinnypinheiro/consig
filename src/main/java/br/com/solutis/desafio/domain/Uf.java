package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.Pais;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Uf implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = true, foreignKey = @ForeignKey(name = "fk_uf_pais"))    private Pais pais_id;

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
    public Pais getPais_id() {
        return pais_id;
    }

    public void setPais_id(Pais pais_id) {
        this.pais_id = pais_id;
    }
}
