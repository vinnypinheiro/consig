package br.com.solutis.desafio.helper.filter;


import java.time.LocalDate;

public class BalancoFilter {


    private int associacaoId;
    private int convenioid;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public int getAssociacaoId() {
        return associacaoId;
    }

    public void setAssociacaoId(int associacaoId) {
        this.associacaoId = associacaoId;
    }

    public int getConvenioid() {
        return convenioid;
    }

    public void setConvenioid(int convenioid) {
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

