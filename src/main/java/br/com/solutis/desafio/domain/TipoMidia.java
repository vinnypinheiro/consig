package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.Material;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoMidia implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = true, foreignKey = @ForeignKey(name = "fk_tipomidia_material"))    private Material material_id;

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
    public Material getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Material material_id) {
        this.material_id = material_id;
    }
}
