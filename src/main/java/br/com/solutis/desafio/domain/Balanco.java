package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Balanco implements Serializable {

    @Id
    private String associado;
    private String cpf;
    private String associacao;
    private String convenio;
    private String situacaoParcela;
    private String parcela;
    private Double vlrParcela;
    private String numeroProposta;
    private String periodo;


    public String getAssociado() {
        return associado;
    }

    public void setAssociado(String associado) {
        this.associado = associado;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAssociacao() {
        return associacao;
    }

    public void setAssociacao(String associacao) {
        this.associacao = associacao;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getSituacaoParcela() {
        return situacaoParcela;
    }

    public void setSituacaoParcela(String situacaoParcela) {
        this.situacaoParcela = situacaoParcela;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public Double getVlrParcela() {
        return vlrParcela;
    }

    public void setVlrParcela(Double vlrParcela) {
        this.vlrParcela = vlrParcela;
    }

    public String getNumeroProposta() {
        return numeroProposta;
    }

    public void setNumeroProposta(String numeroProposta) {
        this.numeroProposta = numeroProposta;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }


}
