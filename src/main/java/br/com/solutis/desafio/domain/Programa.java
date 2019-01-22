package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.Veiculo;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Programa implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String descricao;
    private String horario;
    private  Double valor;

    @JsonBackReference(value="veiculo-programa")
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = true, foreignKey = @ForeignKey(name = "fk_programa_veiculo"))    private Veiculo veiculo_id;


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
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Veiculo getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Veiculo veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
