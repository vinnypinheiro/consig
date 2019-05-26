package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EspelhoRetorno implements Serializable {
    @Id
    @GeneratedValue
    private Long id;


    @Temporal(TemporalType.DATE)
    private Date data;

    private Integer mesreferencia;
    private Double vlrParcela;
    private Double vlrDescontado;
    private String parcela;
    private String cpf;



    @ManyToOne
    @JoinColumn(name = "associado_id", nullable = true, foreignKey = @ForeignKey(name = "fk_espelho_associado"))    private Associado associado_id;

    @ManyToOne
    @JoinColumn(name = "auxilio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_espelho_auxilio"))    private Auxilio auxilio_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getMesreferencia() {
        return mesreferencia;
    }

    public void setMesreferencia(Integer mesreferencia) {
        this.mesreferencia = mesreferencia;
    }

    public Double getVlrParcela() {
        return vlrParcela;
    }

    public void setVlrParcela(Double vlrParcela) {
        this.vlrParcela = vlrParcela;
    }

    public Double getVlrDescontado() {
        return vlrDescontado;
    }

    public void setVlrDescontado(Double vlrDescontado) {
        this.vlrDescontado = vlrDescontado;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Associado getAssociado_id() {
        return associado_id;
    }

    public void setAssociado_id(Associado associado_id) {
        this.associado_id = associado_id;
    }

    public Auxilio getAuxilio_id() {
        return auxilio_id;
    }

    public void setAuxilio_id(Auxilio auxilio_id) {
        this.auxilio_id = auxilio_id;
    }
}
