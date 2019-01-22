package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import br.com.solutis.desafio.domain.Uf;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente implements Serializable {
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


    @JsonManagedReference(value="cliente-endereco")
    @OneToMany(mappedBy = "cliente_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Endereco> enderecoList = new ArrayList<>();

    @JsonManagedReference(value="cliente-contato")
    @OneToMany(mappedBy = "cliente_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contato> contatoList = new ArrayList<>();

    @JsonManagedReference(value="cliente-telefone")
    @OneToMany(mappedBy = "cliente_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Telefone> telefoneList = new ArrayList<>();

    @JsonManagedReference(value="cliente-email")
    @OneToMany(mappedBy = "cliente_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Email> emailList = new ArrayList<>();

    @JsonManagedReference(value="cliente-site")
    @OneToMany(mappedBy = "cliente_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Site> siteList = new ArrayList<>();

    @JsonManagedReference(value="cliente-contabancaria")
    @OneToMany(mappedBy = "cliente_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContaBancaria> contaBancariaList = new ArrayList<>();

 public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
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

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setData_cadastro(Instant data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getInscricaoestadual() {
        return inscricaoestadual;
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
}
