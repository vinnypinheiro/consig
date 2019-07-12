package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Parcela implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Double valor;
    private int parcela;
    private LocalDate datavencimento;
    private LocalDate datavencimento1;
    private String valortotal;
    private LocalDate datapagamento;
    private Double valorpago;
    private String status;
    private LocalDate data;
    private String oplock;
    private String qtdparcelasquitar;

    @JsonBackReference(value="auxilio-parcela")
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "auxilio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_convenio_associacao"))    private Auxilio auxilio_id;


    public String getQtdparcelasquitar() {
        return qtdparcelasquitar;
    }

    public void setQtdparcelasquitar(String qtdparcelasquitar) {
        this.qtdparcelasquitar = qtdparcelasquitar;
    }

    public LocalDate getDatavencimento1() {
        return datavencimento1;
    }

    public void setDatavencimento1(LocalDate datavencimento1) {
        this.datavencimento1 = datavencimento1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(LocalDate datavencimento) {
        this.datavencimento = datavencimento;
    }

    public String getValortotal() {
        return valortotal;
    }

    public void setValortotal(String valortotal) {
        this.valortotal = valortotal;
    }

    public Double getValorpago() {
        return valorpago;
    }

    public void setValorpago(Double valorpago) {
        this.valorpago = valorpago;
    }

    public LocalDate getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(LocalDate datapagamento) {
        this.datapagamento = datapagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Auxilio getAuxilio_id() {
        return auxilio_id;
    }

    public void setAuxilio_id(Auxilio auxilio_id) {
        this.auxilio_id = auxilio_id;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public String getOplock() {
        return oplock;
    }

    public void setOplock(String oplock) {
        this.oplock = oplock;
    }
}
