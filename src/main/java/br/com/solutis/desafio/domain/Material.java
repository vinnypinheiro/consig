package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.TipoMidia;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Material implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String descricao;
    @ManyToOne
    @JoinColumn(name = "tipomidia_id", nullable = true, foreignKey = @ForeignKey(name = "fk_material_tipomidia"))    private TipoMidia tipo_midia_id;

 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public TipoMidia getTipo_midia_id() {
        return tipo_midia_id;
    }

    public void setTipo_midia_id(TipoMidia tipo_midia_id) {
        this.tipo_midia_id = tipo_midia_id;
    }
}
