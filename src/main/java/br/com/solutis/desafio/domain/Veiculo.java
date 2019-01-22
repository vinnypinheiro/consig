package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Veiculo implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String cnpj;
    private String cpf;
    private Instant data_cadastro;
    private String inscricaoestadual;
    private String inscricaomunicipal;
    private String nomefantasia;
    private String obs;
    private String razaosocial;
    private String estado;


    @JsonManagedReference(value="veiculo-endereco")
    @OneToMany(mappedBy = "veiculo_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Endereco> enderecoList = new ArrayList<>();

    @JsonManagedReference(value="veiculo-contato")
    @OneToMany(mappedBy = "veiculo_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contato> contatoList = new ArrayList<>();

    @JsonManagedReference(value="veiculo-telefone")
    @OneToMany(mappedBy = "veiculo_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Telefone> telefoneList = new ArrayList<>();

    @JsonManagedReference(value="veiculo-email")
    @OneToMany(mappedBy = "veiculo_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Email> emailList = new ArrayList<>();

    @JsonManagedReference(value="veiculo-site")
    @OneToMany(mappedBy = "veiculo_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Site> siteList = new ArrayList<>();

    @JsonManagedReference(value="veiculo-contabancaria")
    @OneToMany(mappedBy = "veiculo_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContaBancaria> contaBancariaList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tipomidia_id", nullable = true, foreignKey = @ForeignKey(name = "fk_veiculo_tipomidia"))    private TipoMidia tipomidia_id;

    @JsonManagedReference(value="veiculo-programa")
    @OneToMany(mappedBy = "veiculo_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Programa> programalist = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Instant getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Instant data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getInscricaoestadual() {
        return inscricaoestadual;
    }

    public void setInscricaoestadual(String inscricaoestadual) {
        this.inscricaoestadual = inscricaoestadual;
    }

    public String getInscricaomunicipal() {
        return inscricaomunicipal;
    }

    public void setInscricaomunicipal(String inscricaomunicipal) {
        this.inscricaomunicipal = inscricaomunicipal;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }



    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }

    public List<ContaBancaria> getContaBancariaList() {
        return contaBancariaList;
    }

    public void setContaBancariaList(List<ContaBancaria> contaBancariaList) {
        this.contaBancariaList = contaBancariaList;
    }

    public TipoMidia getTipomidia_id() {
        return tipomidia_id;
    }

    public void setTipomidia_id(TipoMidia tipomidia_id) {
        this.tipomidia_id = tipomidia_id;
    }

    public List<Programa> getProgramalist() {
        return programalist;
    }

    public void setProgramalist(List<Programa> programalist) {
        this.programalist = programalist;
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
