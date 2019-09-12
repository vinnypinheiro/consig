package br.com.solutis.desafio.helper.filter;


import java.time.LocalDate;

public class BalancoFilter {


    private String associacaoId;
    private String convenioid;
    private String situacao;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getAssociacaoId() {
        return associacaoId;
    }

    public void setAssociacaoId(String associacaoId) {
        this.associacaoId = associacaoId;
    }

    public String getConvenioid() {
        return convenioid;
    }

    public void setConvenioid(String convenioid) {
        this.convenioid = convenioid;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}

