package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RemessaAlba implements Serializable {
    @Id
    @GeneratedValue
    private Long id;



    private LocalDate dataEnvio;
    private String cpf;
    private String nome;
    private Double valor;
    private Double valorPago;
    private String status;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "relatorio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_remessa_relatorio"))    private RelatorioAlba relatorio_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RelatorioAlba getRelatorio_id() {
        return relatorio_id;
    }

    public void setRelatorio_id(RelatorioAlba relatorio_id) {
        this.relatorio_id = relatorio_id;
    }
}
