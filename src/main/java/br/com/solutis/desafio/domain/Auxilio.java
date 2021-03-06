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
public class Auxilio implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate dataContrato;

    private LocalDate datareserva;

    @Temporal(TemporalType.DATE)
    private Date data;

    private String numeroproposta;
    private Double vlrauxilio;
    private String vlrauxextenso;
    private Integer qtdparcelas;
    private Integer qtdparcelaspagas;
    private Integer qtdparcelasnaopagas;
    private Double vlrparcelas;
    private Double vlrtotal;
    private Double totalaberto;
    private Double totalpago;
    private Integer porcentagem;
    private String tipo;
    private  String status;
    private String oplock;
    private String arquivo;
    private Double vlroperacao;
    private Double vlrliquidoliberado;
    private String importacao;
    private String qtdparcelasquitar;
    private Double vlrliquidadorefi;



    @JsonBackReference(value="associado-auxilio")
    @ManyToOne
    @JoinColumn(name = "associado_id", nullable = true, foreignKey = @ForeignKey(name = "fk_auxilio_associado"))    private Associado associado_id;

    @JsonBackReference(value="associacao-auxilio")
    @ManyToOne
    @JoinColumn(name = "associacao_id", nullable = true, foreignKey = @ForeignKey(name = "fk_auxilio_associacao"))    private Associacao associacao_id;


    @ManyToOne
    @JoinColumn(name = "correspondente_id", nullable = true, foreignKey = @ForeignKey(name = "fk_auxilio_correspondente"))    private Correspondente correspondente_id;


    @ManyToOne
    @JoinColumn(name = "corretor_id", nullable = true, foreignKey = @ForeignKey(name = "fk_auxilio_corretor"))    private Corretor corretor_id;

    @JsonBackReference(value="convenio-auxilio")
    @ManyToOne
    @JoinColumn(name = "convenio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_auxilio_convenio"))    private Convenio convenio_id;

    @JsonBackReference(value="verbadesconto-auxilio")
    @ManyToOne
    @JoinColumn(name = "verbadesconto_id", nullable = true, foreignKey = @ForeignKey(name = "fk_auxilio_verbadesconto"))    private VerbaDesconto verbadesconto_id;

    @JsonManagedReference(value="auxilio-parcela")
    @OneToMany(mappedBy = "auxilio_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Parcela> parcelaList = new ArrayList<>();

    public Double getVlrliquidadorefi() {
        return vlrliquidadorefi;
    }

    public void setVlrliquidadorefi(Double vlrliquidadorefi) {
        this.vlrliquidadorefi = vlrliquidadorefi;
    }

    public Double getVlrliquidoliberado() {
        return vlrliquidoliberado;
    }

    public void setVlrliquidoliberado(Double vlrliquidoliberado) {
        this.vlrliquidoliberado = vlrliquidoliberado;
    }

    public String getQtdparcelasquitar() {
        return qtdparcelasquitar;
    }

    public void setQtdparcelasquitar(String qtdparcelasquitar) {
        this.qtdparcelasquitar = qtdparcelasquitar;
    }

    public String getImportacao() {
        return importacao;
    }

    public void setImportacao(String importacao) {
        this.importacao = importacao;
    }

    public Integer getQtdparcelasnaopagas() {
        return qtdparcelasnaopagas;
    }

    public void setQtdparcelasnaopagas(Integer qtdparcelasnaopagas) {
        this.qtdparcelasnaopagas = qtdparcelasnaopagas;
    }

    public Integer getQtdparcelaspagas() {
        return qtdparcelaspagas;
    }

    public void setQtdparcelaspagas(Integer qtdparcelaspagas) {
        this.qtdparcelaspagas = qtdparcelaspagas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalaberto() {
        return totalaberto;
    }

    public void setTotalaberto(Double totalaberto) {
        this.totalaberto = totalaberto;
    }

    public Corretor getCorretor_id() {
        return corretor_id;
    }

    public void setCorretor_id(Corretor corretor_id) {
        this.corretor_id = corretor_id;
    }

    public Double getVlroperacao() {
        return vlroperacao;
    }

    public void setVlroperacao(Double vlroperacao) {
        this.vlroperacao = vlroperacao;
    }

    public Double getTotalpago() {
        return totalpago;
    }

    public void setTotalpago(Double totalpago) {
        this.totalpago = totalpago;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNumeroproposta() {
        return numeroproposta;
    }

    public void setNumeroproposta(String numeroproposta) {
        this.numeroproposta = numeroproposta;
    }

    public Double getVlrauxilio() {
        return vlrauxilio;
    }

    public void setVlrauxilio(Double vlrauxilio) {
        this.vlrauxilio = vlrauxilio;
    }

    public String getVlrauxextenso() {
        return vlrauxextenso;
    }

    public void setVlrauxextenso(String vlrauxextenso) {
        this.vlrauxextenso = vlrauxextenso;
    }

    public Integer getQtdparcelas() {
        return qtdparcelas;
    }

    public void setQtdparcelas(Integer qtdparcelas) {
        this.qtdparcelas = qtdparcelas;
    }

    public Double getVlrparcelas() {
        return vlrparcelas;
    }

    public void setVlrparcelas(Double vlrparcelas) {
        this.vlrparcelas = vlrparcelas;
    }

    public Associado getAssociado_id() {
        return associado_id;
    }

    public void setAssociado_id(Associado associado_id) {
        this.associado_id = associado_id;
    }


    public List<Parcela> getParcelaList() {
        return parcelaList;
    }

    public void setParcelaList(List<Parcela> parcelaList) {
        this.parcelaList = parcelaList;
    }

    public Associacao getAssociacao_id() {
        return associacao_id;
    }

    public void setAssociacao_id(Associacao associacao_id) {
        this.associacao_id = associacao_id;
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

    public LocalDate getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(LocalDate dataContrato) {
        this.dataContrato = dataContrato;
    }

    public VerbaDesconto getVerbadesconto_id() {
        return verbadesconto_id;
    }

    public void setVerbadesconto_id(VerbaDesconto verbadesconto_id) {
        this.verbadesconto_id = verbadesconto_id;
    }

    public Integer getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Integer porcentagem) {
        this.porcentagem = porcentagem;
    }

    public LocalDate getDatareserva() {
        return datareserva;
    }

    public void setDatareserva(LocalDate datareserva) {
        this.datareserva = datareserva;
    }

    public Double getVlrtotal() {
        return vlrtotal;
    }

    public void setVlrtotal(Double vlrtotal) {
        this.vlrtotal = vlrtotal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOplock() {
        return oplock;
    }

    public void setOplock(String oplock) {
        this.oplock = oplock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
