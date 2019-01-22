package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.Uf;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Agencia implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String bairro;
    private String banco;
    private String bancoagencia;
    private String bancoconta;
    private String cidade;
    private String cnpj;
    private String contato;
    private String cpf;
    private Instant datacadastro;
    private String email;
    private String email1;
    private String email2;
    private String emailcontato;
    private String endereco;
    private String fax;
    private String inscricaoestadual;
    private String inscricaomunicipal;
    private String nomefantasia;
    private String obs;
    private String razaosocial;
    private String site;
    private String telefone;
    private String telefone2;
    @ManyToOne
    @JoinColumn(name = "uf_id", nullable = true, foreignKey = @ForeignKey(name = "fk_agencia_uf"))    private Uf uf_id;

 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    public String getBancoagencia() {
        return bancoagencia;
    }

    public void setBancoagencia(String bancoagencia) {
        this.bancoagencia = bancoagencia;
    }
    public String getBancoconta() {
        return bancoconta;
    }

    public void setBancoconta(String bancoconta) {
        this.bancoconta = bancoconta;
    }
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Instant getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Instant datacadastro) {
        this.datacadastro = datacadastro;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }
    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }
    public String getEmailcontato() {
        return emailcontato;
    }

    public void setEmailcontato(String emailcontato) {
        this.emailcontato = emailcontato;
    }
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
    public Uf getUf_id() {
        return uf_id;
    }

    public void setUf_id(Uf uf_id) {
        this.uf_id = uf_id;
    }
}
