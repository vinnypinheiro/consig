package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.Cliente;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Campanha implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Instant datafim;
    private Instant datainicio;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true, foreignKey = @ForeignKey(name = "fk_campanha_cliente"))    private Cliente cliente_id;

 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public Instant getDatafim() {
        return datafim;
    }

    public void setDatafim(Instant datafim) {
        this.datafim = datafim;
    }
    public Instant getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Instant datainicio) {
        this.datainicio = datainicio;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }
}
