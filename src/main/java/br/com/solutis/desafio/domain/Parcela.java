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
    private String datavencimento;
    private String valortotal;
    private String datapagamento;
    private String status;
    private LocalDate data;

    @JsonBackReference(value="auxilio-parcela")
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "auxilio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_convenio_associacao"))    private Auxilio auxilio_id;


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

    public String getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(String datavencimento) {
        this.datavencimento = datavencimento;
    }

    public String getValortotal() {
        return valortotal;
    }

    public void setValortotal(String valortotal) {
        this.valortotal = valortotal;
    }

    public String getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(String datapagamento) {
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
}