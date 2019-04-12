package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ocorrencia implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String descricao;
    private String observacao;
    private LocalDate data;

    @JsonBackReference(value="associado-ocorrencia")
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "associado_id", nullable = true, foreignKey = @ForeignKey(name = "fk_mensalidade_associado"))    private Associado associado_id;


    public Associado getAssociado_id() {
        return associado_id;
    }

    public void setAssociado_id(Associado associado_id) {
        this.associado_id = associado_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
