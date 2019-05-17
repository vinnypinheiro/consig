package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssociacaoLink implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference(value="associacao-associado")
    @ManyToOne
    @JoinColumn(name = "associado_id", nullable = true, foreignKey = @ForeignKey(name = "fk_associacao-associado"))    private Associado associado_id;

    @JsonBackReference(value="associacao-associacao")
    @ManyToOne
    @JoinColumn(name = "associacao_id", nullable = true, foreignKey = @ForeignKey(name = "fk_associacao-associacao"))    private Associacao associacao_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Associado getAssociado_id() {
        return associado_id;
    }

    public void setAssociado_id(Associado associado_id) {
        this.associado_id = associado_id;
    }

    public Associacao getAssociacao_id() {
        return associacao_id;
    }

    public void setAssociacao_id(Associacao associacao_id) {
        this.associacao_id = associacao_id;
    }
}
