package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RetornoAlba implements Serializable {
    @Id
    @GeneratedValue
    private Long id;



    private String mes;
    private String ano;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "relatorio_id", nullable = true, foreignKey = @ForeignKey(name = "fk_remessa_retorno"))    private RelatorioAlba relatorio_id;

    @OneToMany(mappedBy = "relatorio_id", orphanRemoval = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RetornoAlba> retornoList = new ArrayList<>();


}
