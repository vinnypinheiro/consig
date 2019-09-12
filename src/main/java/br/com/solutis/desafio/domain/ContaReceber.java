package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContaReceber implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String cpf;
    private String parcela;
    private Double valor;
    private String situacao;
    private String observacao;
    private String oplock;
    private LocalDate dataVencimento;
    private LocalDate dataLancamaneto;

    @Transient
    @ManyToOne
    @JoinColumn(name = "associado_id", nullable = true, foreignKey = @ForeignKey(name = "fk_contareceber_associado"))    private Associado associado_id;

    @Transient
    @ManyToOne
    @JoinColumn(name = "correspondente_id", nullable = true, foreignKey = @ForeignKey(name = "fk_contareceber_correspondente"))    private Correspondente correspondente_id;

    @Transient
    @ManyToOne
    @JoinColumn(name = "convenio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_contareceber_convenio"))    private Convenio convenio_id;

    @Transient
    @ManyToOne
    @JoinColumn(name = "verbadesconto_id", nullable = true, foreignKey = @ForeignKey(name = "fk_contareceber_verbadesconto"))    private VerbaDesconto verbadesconto_id;

    @Transient
    @ManyToOne
    @JoinColumn(name = "associacao_id", nullable = true, foreignKey = @ForeignKey(name = "fk_contareceber_associacao"))    private Associacao associacao_id;

    public LocalDate getDataLancamaneto() {
        return dataLancamaneto;
    }

    public void setDataLancamaneto(LocalDate dataLancamaneto) {
        this.dataLancamaneto = dataLancamaneto;
    }

    public Associacao getAssociacao_id() {
        return associacao_id;
    }

    public void setAssociacao_id(Associacao associacao_id) {
        this.associacao_id = associacao_id;
    }

    public VerbaDesconto getVerbadesconto_id() {
        return verbadesconto_id;
    }

    public void setVerbadesconto_id(VerbaDesconto verbadesconto_id) {
        this.verbadesconto_id = verbadesconto_id;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Correspondente getCorrespondente_id() {
        return correspondente_id;
    }

    public void setCorrespondente_id(Correspondente correspondente_id) {
        this.correspondente_id = correspondente_id;
    }

    public Convenio getConvenio_id() {
        return convenio_id;
    }

    public void setConvenio_id(Convenio convenio_id) {
        this.convenio_id = convenio_id;
    }

    public Associado getAssociado_id() {
        return associado_id;
    }

    public void setAssociado_id(Associado associado_id) {
        this.associado_id = associado_id;
    }

    public String getOplock() {
        return oplock;
    }

    public void setOplock(String oplock) {
        this.oplock = oplock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
