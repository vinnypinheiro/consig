package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.solutis.desafio.domain.Agencia;
import br.com.solutis.desafio.domain.Campanha;
import br.com.solutis.desafio.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlanoMidia implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String codigo;
    private Date hora;
    private String mercado;
    private String obs;
    private Date data;
    private String programa;

    @ManyToOne
    @JoinColumn(name = "agencia_id", nullable = true, foreignKey = @ForeignKey(name = "fk_planomidia_agencia"))    private Agencia agencia_id;
    @ManyToOne
    @JoinColumn(name = "campanha_id", nullable = true, foreignKey = @ForeignKey(name = "fk_planomidia_campanha"))    private Campanha campanha_id;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true, foreignKey = @ForeignKey(name = "fk_planomidia_cliente"))    private Cliente cliente_id;

    @JsonManagedReference(value="peca-planomidia")
    @OneToMany(mappedBy = "planomidia_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Peca> pecalist = new ArrayList<>();

    @JsonManagedReference(value="pedidoinsercaoitem-planomidia")
    @OneToMany(mappedBy = "planomidia_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PedidoInsercaoItem> pedidoDeInsercaoItemlist = new ArrayList<>();



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
    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }
    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }
    public Agencia getAgencia_id() {
        return agencia_id;
    }

    public void setAgencia_id(Agencia agencia_id) {
        this.agencia_id = agencia_id;
    }
    public Campanha getCampanha_id() {
        return campanha_id;
    }

    public void setCampanha_id(Campanha campanha_id) {
        this.campanha_id = campanha_id;
    }
    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }


    public List<Peca> getPecalist() {
        return pecalist;
    }

    public void setPecalist(List<Peca> pecalist) {
        this.pecalist = pecalist;
    }


    public List<PedidoInsercaoItem> getPedidoDeInsercaoItemlist() {
        return pedidoDeInsercaoItemlist;
    }

    public void setPedidoDeInsercaoItemlist(List<PedidoInsercaoItem> pedidoDeInsercaoItemlist) {
        this.pedidoDeInsercaoItemlist = pedidoDeInsercaoItemlist;
    }


}


