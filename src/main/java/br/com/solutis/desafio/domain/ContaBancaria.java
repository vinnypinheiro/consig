package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContaBancaria implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String banco;
    private String agencia;
    private String conta;
    private String operacao;
    private  String tipoconta;
    private String cnpjcpf;
    private String favorecido;


    @JsonBackReference(value="veiculo-contabancaria")
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = true, foreignKey = @ForeignKey(name = "fk_contabancaria_veiculo"))    private Veiculo veiculo_id;

    @JsonBackReference(value="cliente-contabancaria")
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true, foreignKey = @ForeignKey(name = "fk_contabancaria_cliente"))    private Cliente cliente_id;



 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getTipoconta() {
        return tipoconta;
    }

    public void setTipoconta(String tipoconta) {
        this.tipoconta = tipoconta;
    }

    public String getCnpjcpf() {
        return cnpjcpf;
    }

    public void setCnpjcpf(String cnpjcpf) {
        this.cnpjcpf = cnpjcpf;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
    }

    public Veiculo getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Veiculo veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }
}
