package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParcelaAlba implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Double valorTotal;
    private Double valorTotalPago;
    private String status;
    private String ano;
    private String mes;
    private String oplock;
    private String nome;
    private String cpf;
    private LocalDate dataEnvio;
    private String matricula;
    private Integer qtdparcelasenviadas;
    private Integer qtdparcelasaverbadas;
    private Integer qtdglosa;


//    @ManyToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "auxilio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_convenio_associacao"))    private Auxilio auxilio_id;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "relatorio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_parcelalaba_relatorio"))    private RelatorioAlba relatorio_id;

    public Integer getQtdparcelasenviadas() {
        return qtdparcelasenviadas;
    }

    public void setQtdparcelasenviadas(Integer qtdparcelasenviadas) {
        this.qtdparcelasenviadas = qtdparcelasenviadas;
    }

    public Integer getQtdparcelasaverbadas() {
        return qtdparcelasaverbadas;
    }

    public void setQtdparcelasaverbadas(Integer qtdparcelasaverbadas) {
        this.qtdparcelasaverbadas = qtdparcelasaverbadas;
    }

    public Integer getQtdglosa() {
        return qtdglosa;
    }

    public void setQtdglosa(Integer qtdglosa) {
        this.qtdglosa = qtdglosa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorTotalPago() {
        return valorTotalPago;
    }

    public void setValorTotalPago(Double valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getOplock() {
        return oplock;
    }

    public void setOplock(String oplock) {
        this.oplock = oplock;
    }

    public RelatorioAlba getRelatorio_id() {
        return relatorio_id;
    }

    public void setRelatorio_id(RelatorioAlba relatorio_id) {
        this.relatorio_id = relatorio_id;
    }
}
