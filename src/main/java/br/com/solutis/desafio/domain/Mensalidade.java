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
public class Mensalidade implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Date dataAssociacao;

    @Temporal(TemporalType.DATE)
    private Date data;

    private Integer mensalidade;
    private String statuspagamento;
    private LocalDate dataprocesamento;
    private String numeroproposta;
    private Double vlrmensalidade;
    private Double vlrdescontado;
    private String mesanoref;
    private String vlrauxextenso;
    private String oplock;
    private LocalDate datavencimento;

    public String getOplock() {
        return oplock;
    }

    public void setOplock(String oplock) {
        this.oplock = oplock;
    }

    @JsonBackReference(value="associado-mensalidade")
    @ManyToOne
    @JoinColumn(name = "associado_id", nullable = true, foreignKey = @ForeignKey(name = "fk_mensalidade_associado"))    private Associado associado_id;


    @JsonBackReference(value="verbadesconto-mensalidade")
    @ManyToOne
    @JoinColumn(name = "verbadesconto_id", nullable = true, foreignKey = @ForeignKey(name = "fk_mensalidade_verbadesconto"))   private VerbaDesconto verbadesconto_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAssociacao() {
        return dataAssociacao;
    }

    public void setDataAssociacao(Date dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Integer mensalidade) {
        this.mensalidade = mensalidade;
    }

    public String getStatuspagamento() {
        return statuspagamento;
    }

    public void setStatuspagamento(String statuspagamento) {
        this.statuspagamento = statuspagamento;
    }

    public LocalDate getDataprocesamento() {
        return dataprocesamento;
    }

    public void setDataprocesamento(LocalDate dataprocesamento) {
        this.dataprocesamento = dataprocesamento;
    }

    public LocalDate getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(LocalDate datavencimento) {
        this.datavencimento = datavencimento;
    }

    public String getNumeroproposta() {
        return numeroproposta;
    }

    public void setNumeroproposta(String numeroproposta) {
        this.numeroproposta = numeroproposta;
    }

    public Double getVlrmensalidade() {
        return vlrmensalidade;
    }

    public void setVlrmensalidade(Double vlrmensalidade) {
        this.vlrmensalidade = vlrmensalidade;
    }

    public String getMesanoref() {
        return mesanoref;
    }

    public void setMesanoref(String mesanoref) {
        this.mesanoref = mesanoref;
    }

    public String getVlrauxextenso() {
        return vlrauxextenso;
    }

    public void setVlrauxextenso(String vlrauxextenso) {
        this.vlrauxextenso = vlrauxextenso;
    }

    public Associado getAssociado_id() {
        return associado_id;
    }

    public void setAssociado_id(Associado associado_id) {
        this.associado_id = associado_id;
    }

    public VerbaDesconto getVerbadesconto_id() {
        return verbadesconto_id;
    }

    public void setVerbadesconto_id(VerbaDesconto verbadesconto_id) {
        this.verbadesconto_id = verbadesconto_id;
    }

    public Double getVlrdescontado() {
        return vlrdescontado;
    }

    public void setVlrdescontado(Double vlrdescontado) {
        this.vlrdescontado = vlrdescontado;
    }
}
