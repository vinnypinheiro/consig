package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import br.com.solutis.desafio.domain.Cliente;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MailPi implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String assunto;
    private Instant dataenvio;
    private String para;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true, foreignKey = @ForeignKey(name = "fk_mailpi_cliente"))    private Cliente veiculo_id;

 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    public Instant getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(Instant dataenvio) {
        this.dataenvio = dataenvio;
    }
    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }
    public Cliente getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Cliente veiculo_id) {
        this.veiculo_id = veiculo_id;
    }
}
