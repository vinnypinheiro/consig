package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.Formato;
import br.com.solutis.desafio.domain.FormatoRevista;
import br.com.solutis.desafio.domain.Material;
import br.com.solutis.desafio.domain.PlanoMidia;
import br.com.solutis.desafio.domain.TipoMidia;
import br.com.solutis.desafio.domain.TipoMidiaDigital;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Peca implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Float centimetros;
    private String colunas;
    private String duracao;
    private String peca;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "formato_id", nullable = true, foreignKey = @ForeignKey(name = "fk_peca_formato"))    private Formato formato_id;
    @ManyToOne
    @JoinColumn(name = "formatorevista_id", nullable = true, foreignKey = @ForeignKey(name = "fk_peca_formatorevista"))    private FormatoRevista formatorevista_id;
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = true, foreignKey = @ForeignKey(name = "fk_peca_material"))    private Material material_id;

    @ManyToOne
    @JoinColumn(name = "tipomidia_id", nullable = true, foreignKey = @ForeignKey(name = "fk_peca_tipomidia"))    private TipoMidia tipomidia_id;

    @JsonBackReference(value="peca-planomidia")
    @ManyToOne
    @JoinColumn(name = "planomidia_id", nullable = true, foreignKey = @ForeignKey(name = "fk_peca_planomidia"))    private PlanoMidia planomidia_id;

    @ManyToOne
    @JoinColumn(name = "tipomidiadigital_id", nullable = true, foreignKey = @ForeignKey(name = "fk_peca_tipomidiadigital"))    private TipoMidiaDigital tipomidiadigital_id;



 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public Float getCentimetros() {
        return centimetros;
    }

    public void setCentimetros(Float centimetros) {
        this.centimetros = centimetros;
    }
    public String getColunas() {
        return colunas;
    }

    public void setColunas(String colunas) {
        this.colunas = colunas;
    }
    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
    public String getPeca() {
        return peca;
    }

    public void setPeca(String peca) {
        this.peca = peca;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Formato getFormato_id() {
        return formato_id;
    }

    public void setFormato_id(Formato formato_id) {
        this.formato_id = formato_id;
    }
    public FormatoRevista getFormatorevista_id() {
        return formatorevista_id;
    }

    public void setFormatorevista_id(FormatoRevista formatorevista_id) {
        this.formatorevista_id = formatorevista_id;
    }
    public Material getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Material material_id) {
        this.material_id = material_id;
    }


    public TipoMidia getTipomidia_id() {
        return tipomidia_id;
    }

    public void setTipomidia_id(TipoMidia tipomidia_id) {
        this.tipomidia_id = tipomidia_id;
    }
    public TipoMidiaDigital getTipomidiadigital_id() {
        return tipomidiadigital_id;
    }



    public void setTipomidiadigital_id(TipoMidiaDigital tipomidiadigital_id) {
        this.tipomidiadigital_id = tipomidiadigital_id;
    }


    public PlanoMidia getPlanomidia_id() {
        return planomidia_id;
    }

    public void setPlanomidia_id(PlanoMidia planomidia_id) {
        this.planomidia_id = planomidia_id;
    }



}
