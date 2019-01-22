package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Convenio implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String razaosocial;
    private String nomefantasia;
    private String cnpj;
    private String inscricaoestadual;
    private String inscricaomunicipal;
    private String datainicial;
    private String datafinal;

    @JsonBackReference(value="associacao-convenio")
    @ManyToOne
    @JoinColumn(name = "associacao_id", nullable = true, foreignKey = @ForeignKey(name = "fk_convenio_associacao"))   private Associacao associacao_id;

    @JsonManagedReference(value="convenio-auxilio")
    @OneToMany(mappedBy = "convenio_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Auxilio> auxilioList = new ArrayList<>();

    @JsonManagedReference(value="convenio-verbadesconto")
    @OneToMany(mappedBy = "convenio_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VerbaDesconto> verbadescontolist = new ArrayList<>();


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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getDatainicial() {
        return datainicial;
    }

    public void setDatainicial(String datainicial) {
        this.datainicial = datainicial;
    }

    public String getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(String datafinal) {
        this.datafinal = datafinal;
    }

    public Associacao getAssociacao_id() {
        return associacao_id;
    }

    public void setAssociacao_id(Associacao associacao_id) {
        this.associacao_id = associacao_id;
    }

    public List<Auxilio> getAuxilioList() {
        return auxilioList;
    }

    public void setAuxilioList(List<Auxilio> auxilioList) {
        this.auxilioList = auxilioList;
    }

    public List<VerbaDesconto> getVerbadescontolist() {
        return verbadescontolist;
    }

    public void setVerbadescontolist(List<VerbaDesconto> verbadescontolist) {
        this.verbadescontolist = verbadescontolist;
    }
}
