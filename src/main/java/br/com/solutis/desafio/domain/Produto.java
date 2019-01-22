package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.Formato;
import br.com.solutis.desafio.domain.TipoProduto;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Produto implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String descricao;
    private String midia;
    private Double preco;
    @ManyToOne
    @JoinColumn(name = "formato_id", nullable = true, foreignKey = @ForeignKey(name = "fk_produto_formato"))    private Formato formato_id;
    @ManyToOne
    @JoinColumn(name = "tipoproduto_id", nullable = true, foreignKey = @ForeignKey(name = "fk_produto_tipoproduto"))    private TipoProduto tipoproduto_id;

 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getMidia() {
        return midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }
    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public Formato getFormato_id() {
        return formato_id;
    }

    public void setFormato_id(Formato formato_id) {
        this.formato_id = formato_id;
    }
    public TipoProduto getTipoproduto_id() {
        return tipoproduto_id;
    }

    public void setTipoproduto_id(TipoProduto tipoproduto_id) {
        this.tipoproduto_id = tipoproduto_id;
    }
}
