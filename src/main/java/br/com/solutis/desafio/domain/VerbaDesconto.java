package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VerbaDesconto implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private int codigo;
    private String descricao;

    @JsonBackReference(value="convenio-verbadesconto")
    @ManyToOne
    @JoinColumn(name = "convenio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_verbadesconto_convenio"))    private Convenio convenio_id;

    @JsonManagedReference(value="verbadesconto-auxilio")
    @OneToMany(mappedBy = "verbadesconto_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Auxilio> auxilioList = new ArrayList<>();

    @JsonManagedReference(value="verbadesconto-mensalidade")
    @OneToMany(mappedBy = "verbadesconto_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mensalidade> mensalidadeList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Convenio getConvenio_id() {
        return convenio_id;
    }

    public void setConvenio_id(Convenio convenio_id) {
        this.convenio_id = convenio_id;
    }

    public List<Auxilio> getAuxilioList() {
        return auxilioList;
    }

    public void setAuxilioList(List<Auxilio> auxilioList) {
        this.auxilioList = auxilioList;
    }

    public List<Mensalidade> getMensalidadeList() {
        return mensalidadeList;
    }

    public void setMensalidadeList(List<Mensalidade> mensalidadeList) {
        this.mensalidadeList = mensalidadeList;
    }
}
