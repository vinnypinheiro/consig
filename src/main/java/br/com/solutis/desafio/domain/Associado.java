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
@Table(
        name = "associado",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf"})}
)
public class Associado implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private Long cpf;
    private String  matricula;
    private String cidade;
    private String  bairro;
    private String  uf;
    private String  cep;
    private String  situacao;
    private String  lotacao;
    private String  cargo;
    private String  telefone;
    private String  email;
    private String  endereco;
    private String  estado;
    private String  municipio;
    private String  datacadastro;
    private String  banco;
    private String  agencia;
    private String  tipoconta;
    private String  conta;
    private String orgao;
    private String rg;

    private String asscontrato;
    private Double vlrmensalidade;
    private String oplock;
    private String obs;




    @ManyToOne
    @JoinColumn(name = "associacao1", nullable = true, foreignKey = @ForeignKey(name = "fk_associado-associacao1"))   private Associacao associacao1;


    @ManyToOne
    @JoinColumn(name = "associacao2", nullable = true, foreignKey = @ForeignKey(name = "fk_associado-associacao1"))   private Associacao associacao2;


    @Transient
    @JsonManagedReference(value="associado-auxilio")
    @OneToMany(mappedBy = "associado_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Auxilio> auxilioList = new ArrayList<>();

    @Transient
    @JsonManagedReference(value="associado-mensalidade")
    @OneToMany(mappedBy = "associado_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mensalidade> mensalidadeList = new ArrayList<>();


    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Associacao getAssociacao1() {
        return associacao1;
    }

    public void setAssociacao1(Associacao associacao1) {
        this.associacao1 = associacao1;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Associacao getAssociacao2() {
        return associacao2;
    }

    public void setAssociacao2(Associacao associacao2) {
        this.associacao2 = associacao2;
    }

    public Double getVlrmensalidade() {
        return vlrmensalidade;
    }

    public void setVlrmensalidade(Double vlrmensalidade) {
        this.vlrmensalidade = vlrmensalidade;
    }

    public List<Mensalidade> getMensalidadeList() {
        return mensalidadeList;
    }

    public void setMensalidadeList(List<Mensalidade> mensalidadeList) {
        this.mensalidadeList = mensalidadeList;
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

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getAsscontrato() {
        return asscontrato;
    }

    public void setAsscontrato(String asscontrato) {
        this.asscontrato = asscontrato;
    }


    public List<Auxilio> getAuxilioList() {
        return auxilioList;
    }

    public void setAuxilioList(List<Auxilio> auxilioList) {
        this.auxilioList = auxilioList;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getLotacao() {
        return lotacao;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(String tipoconta) {
        this.tipoconta = tipoconta;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getOplock() {
        return oplock;
    }

    public void setOplock(String oplock) {
        this.oplock = oplock;
    }
}
