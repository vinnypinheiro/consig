package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Site implements Serializable {
    @Id
    @GeneratedValue
    private Long id;



    private String tiposite;
    private String site;

    @JsonBackReference(value="veiculo-site")
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = true, foreignKey = @ForeignKey(name = "fk_site_veiculo"))    private Veiculo veiculo_id;

    @JsonBackReference(value="cliente-site")
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true, foreignKey = @ForeignKey(name = "fk_site_cliente"))    private Cliente cliente_id;


    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Veiculo veiculo_id) {
        this.veiculo_id = veiculo_id;
    }


    public String getTiposite() {
        return tiposite;
    }

    public void setTiposite(String tiposite) {
        this.tiposite = tiposite;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }


}
