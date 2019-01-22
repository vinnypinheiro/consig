package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Telefone implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    private String tipotelefone;
    private String numero;

    @JsonBackReference(value="veiculo-telefone")
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = true, foreignKey = @ForeignKey(name = "fk_telefone_veiculo"))    private Veiculo veiculo_id;

    @JsonBackReference(value="cliente-telefone")
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true, foreignKey = @ForeignKey(name = "fk_telefone_cliente"))    private Cliente cliente_id;


    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTipotelefone() {
        return tipotelefone;
    }

    public void setTipotelefone(String tipotelefone) {
        this.tipotelefone = tipotelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Veiculo getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Veiculo veiculo_id) {
        this.veiculo_id = veiculo_id;
    }


}
