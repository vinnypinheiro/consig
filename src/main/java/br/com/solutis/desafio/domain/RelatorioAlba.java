package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RelatorioAlba implements Serializable {
    @Id
    @GeneratedValue
    private Long id;



    private String mes;
    private String ano;
    private String Unidade;

    private Double totalEnviado;
    private Double totalAverbado;
    private String totalFuncionarios;
    private Integer totalFuncionariosRetorno;
    private LocalDate dataProcessamento;


    @OneToMany(mappedBy = "relatorio_id", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<ParcelaAlba> parcelaList = new ArrayList<>();


    public LocalDate getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(LocalDate dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public Integer getTotalFuncionariosRetorno() {
        return totalFuncionariosRetorno;
    }

    public void setTotalFuncionariosRetorno(Integer totalFuncionariosRetorno) {
        this.totalFuncionariosRetorno = totalFuncionariosRetorno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getUnidade() {
        return Unidade;
    }

    public void setUnidade(String unidade) {
        Unidade = unidade;
    }

    public Double getTotalEnviado() {
        return totalEnviado;
    }

    public void setTotalEnviado(Double totalEnviado) {
        this.totalEnviado = totalEnviado;
    }

    public Double getTotalAverbado() {
        return totalAverbado;
    }

    public void setTotalAverbado(Double totalAverbado) {
        this.totalAverbado = totalAverbado;
    }

    public String getTotalFuncionarios() {
        return totalFuncionarios;
    }

    public void setTotalFuncionarios(String totalFuncionarios) {
        this.totalFuncionarios = totalFuncionarios;
    }

    public List<ParcelaAlba> getParcelaList() {
        return parcelaList;
    }

    public void setParcelaList(List<ParcelaAlba> parcelaList) {
        this.parcelaList = parcelaList;
    }
}
