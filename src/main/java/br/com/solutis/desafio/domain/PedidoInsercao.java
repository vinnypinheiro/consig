package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.Agencia;
import br.com.solutis.desafio.domain.Cliente;
import br.com.solutis.desafio.domain.Cliente;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PedidoInsercao implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String codigo;
    private String informacoesimportantes;
    private String obs;
    private Long plano_midia_insercao_item_id;
    @ManyToOne
    @JoinColumn(name = "agencia_id", nullable = true, foreignKey = @ForeignKey(name = "fk_pedidoinsercao_agencia"))    private Agencia agencia_id;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true, foreignKey = @ForeignKey(name = "fk_pedidoinsercao_cliente"))    private Cliente cliente_id;
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = true, foreignKey = @ForeignKey(name = "fk_pedidoinsercao_veiculo"))    private Cliente veiculo_id;

 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getInformacoesimportantes() {
        return informacoesimportantes;
    }

    public void setInformacoesimportantes(String informacoesimportantes) {
        this.informacoesimportantes = informacoesimportantes;
    }
    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    public Long getPlano_midia_insercao_item_id() {
        return plano_midia_insercao_item_id;
    }

    public void setPlano_midia_insercao_item_id(Long plano_midia_insercao_item_id) {
        this.plano_midia_insercao_item_id = plano_midia_insercao_item_id;
    }
    public Agencia getAgencia_id() {
        return agencia_id;
    }

    public void setAgencia_id(Agencia agencia_id) {
        this.agencia_id = agencia_id;
    }
    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }
    public Cliente getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Cliente veiculo_id) {
        this.veiculo_id = veiculo_id;
    }
}
