package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Associacao implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String razaosocial;
    private String nomefantasia;
    private String inscricaoestadual;
    private String inscricaomunicipal;
    private String cnpj;
    private String endereco;
    private String cidade;
    private String uf;
    private String cep;
    private String bairro;
    private String telefone;
    private String email;
    private String site;

    @JsonManagedReference(value="associacao-correspondente")
    @OneToMany(mappedBy = "associacao_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Correspondente> correspondenteList = new ArrayList<>();

    @JsonManagedReference(value="associacao-convenio")
    @OneToMany(mappedBy = "associacao_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Convenio> convenioList = new ArrayList<>();

    @JsonManagedReference(value="associacao-auxilio")
    @OneToMany(mappedBy = "associacao_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Auxilio> auxilioList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<Correspondente> getCorrespondenteList() {
        return correspondenteList;
    }

    public void setCorrespondenteList(List<Correspondente> correspondenteList) {
        this.correspondenteList = correspondenteList;
    }

    public List<Convenio> getConvenioList() {
        return convenioList;
    }

    public void setConvenioList(List<Convenio> convenioList) {
        this.convenioList = convenioList;
    }

    public List<Auxilio> getAuxilioList() {
        return auxilioList;
    }

    public void setAuxilioList(List<Auxilio> auxilioList) {
        this.auxilioList = auxilioList;
    }
}
