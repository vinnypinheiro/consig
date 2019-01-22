package br.com.solutis.desafio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import br.com.solutis.desafio.domain.PedidoInsercao;
import br.com.solutis.desafio.domain.Produto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PedidoInsercaoItem implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Date data;
    private String mes;
    private String ano;
    private Double desconto;
    private Double dia01;
    private Double dia02;
    private Double dia03;
    private Double dia04;
    private Double dia05;
    private Double dia06;
    private Double dia07;
    private Double dia08;
    private Double dia09;
    private Double dia10;
    private Double dia11;
    private Double dia12;
    private Double dia13;
    private Double dia14;
    private Double dia15;
    private Double dia16;
    private Double dia17;
    private Double dia18;
    private Double dia19;
    private Double dia20;
    private Double dia21;
    private Double dia22;
    private Double dia23;
    private Double dia24;
    private Double dia25;
    private Double dia26;
    private Double dia27;
    private Double dia28;
    private Double dia29;
    private Double dia30;
    private Double dia31;
    private Date faturamento;
    private String obs;
    private Double qtd;
    private Double valor;
    private Date vencimento;
    private Double valorUnit;


    @ManyToOne
    @JoinColumn(name = "pedidoinsercao_id", nullable = true, foreignKey = @ForeignKey(name = "fk_pedidoinsercaoitem_pedidoinsercao"))    private PedidoInsercao pedidoinsercao_id;


    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = true, foreignKey = @ForeignKey(name = "fk_pedidoinsercaoitem_produto"))    private Produto produto_id;

    @JsonBackReference(value="pedidoinsercaoitem-planomidia")
    @ManyToOne
    @JoinColumn(name = "planomidia_id", nullable = true, foreignKey = @ForeignKey(name = "fk_pedidoinsercaoitem_planomidia"))    private PlanoMidia planomidia_id;


    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = true, foreignKey = @ForeignKey(name = "fk_pedidoinsercaoitem_veiculo"))    private Veiculo veiculo_id ;

    @ManyToOne
    @JoinColumn(name = "peca_id", nullable = true, foreignKey = @ForeignKey(name = "fk_pedidoinsercaoitem_peca"))    private Peca peca_id ;

    @ManyToOne
    @JoinColumn(name = "programa_id", nullable = true, foreignKey = @ForeignKey(name = "fk_pedidoinsercaoitem_programa"))    private Programa programa_id ;



 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    public Double getDia01() {
        return dia01;
    }

    public void setDia01(Double dia01) {
        this.dia01 = dia01;
    }
    public Double getDia02() {
        return dia02;
    }

    public void setDia02(Double dia02) {
        this.dia02 = dia02;
    }
    public Double getDia03() {
        return dia03;
    }

    public void setDia03(Double dia03) {
        this.dia03 = dia03;
    }
    public Double getDia04() {
        return dia04;
    }

    public void setDia04(Double dia04) {
        this.dia04 = dia04;
    }
    public Double getDia05() {
        return dia05;
    }

    public void setDia05(Double dia05) {
        this.dia05 = dia05;
    }
    public Double getDia06() {
        return dia06;
    }

    public void setDia06(Double dia06) {
        this.dia06 = dia06;
    }
    public Double getDia07() {
        return dia07;
    }

    public void setDia07(Double dia07) {
        this.dia07 = dia07;
    }
    public Double getDia08() {
        return dia08;
    }

    public void setDia08(Double dia08) {
        this.dia08 = dia08;
    }
    public Double getDia09() {
        return dia09;
    }

    public void setDia09(Double dia09) {
        this.dia09 = dia09;
    }
    public Double getDia10() {
        return dia10;
    }

    public void setDia10(Double dia10) {
        this.dia10 = dia10;
    }
    public Double getDia11() {
        return dia11;
    }

    public void setDia11(Double dia11) {
        this.dia11 = dia11;
    }
    public Double getDia12() {
        return dia12;
    }

    public void setDia12(Double dia12) {
        this.dia12 = dia12;
    }
    public Double getDia13() {
        return dia13;
    }

    public void setDia13(Double dia13) {
        this.dia13 = dia13;
    }
    public Double getDia14() {
        return dia14;
    }

    public void setDia14(Double dia14) {
        this.dia14 = dia14;
    }
    public Double getDia15() {
        return dia15;
    }

    public void setDia15(Double dia15) {
        this.dia15 = dia15;
    }
    public Double getDia16() {
        return dia16;
    }

    public void setDia16(Double dia16) {
        this.dia16 = dia16;
    }
    public Double getDia17() {
        return dia17;
    }

    public void setDia17(Double dia17) {
        this.dia17 = dia17;
    }
    public Double getDia18() {
        return dia18;
    }

    public void setDia18(Double dia18) {
        this.dia18 = dia18;
    }
    public Double getDia19() {
        return dia19;
    }

    public void setDia19(Double dia19) {
        this.dia19 = dia19;
    }
    public Double getDia20() {
        return dia20;
    }

    public void setDia20(Double dia20) {
        this.dia20 = dia20;
    }
    public Double getDia21() {
        return dia21;
    }

    public void setDia21(Double dia21) {
        this.dia21 = dia21;
    }
    public Double getDia22() {
        return dia22;
    }

    public void setDia22(Double dia22) {
        this.dia22 = dia22;
    }
    public Double getDia23() {
        return dia23;
    }

    public void setDia23(Double dia23) {
        this.dia23 = dia23;
    }
    public Double getDia24() {
        return dia24;
    }

    public void setDia24(Double dia24) {
        this.dia24 = dia24;
    }
    public Double getDia25() {
        return dia25;
    }

    public void setDia25(Double dia25) {
        this.dia25 = dia25;
    }
    public Double getDia26() {
        return dia26;
    }

    public void setDia26(Double dia26) {
        this.dia26 = dia26;
    }
    public Double getDia27() {
        return dia27;
    }

    public void setDia27(Double dia27) {
        this.dia27 = dia27;
    }
    public Double getDia28() {
        return dia28;
    }

    public void setDia28(Double dia28) {
        this.dia28 = dia28;
    }
    public Double getDia29() {
        return dia29;
    }

    public void setDia29(Double dia29) {
        this.dia29 = dia29;
    }
    public Double getDia30() {
        return dia30;
    }

    public void setDia30(Double dia30) {
        this.dia30 = dia30;
    }
    public Double getDia31() {
        return dia31;
    }

    public void setDia31(Double dia31) {
        this.dia31 = dia31;
    }
    public Date getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(Date faturamento) {
        this.faturamento = faturamento;
    }
    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }
    public PedidoInsercao getPedidoinsercao_id() {
        return pedidoinsercao_id;
    }

    public void setPedidoinsercao_id(PedidoInsercao pedidoinsercao_id) {
        this.pedidoinsercao_id = pedidoinsercao_id;
    }
    public Produto getProduto_id() {
        return produto_id;
    }



    public void setProduto_id(Produto produto_id) {
        this.produto_id = produto_id;
    }


    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }


    public PlanoMidia getPlanomidia_id() {
        return planomidia_id;
    }

    public void setPlanomidia_id(PlanoMidia planomidia_id) {
        this.planomidia_id = planomidia_id;
    }

    public Veiculo getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Veiculo veiculo_id) {
        this.veiculo_id = veiculo_id;
    }


    public Peca getPeca_id() {
        return peca_id;
    }

    public void setPeca_id(Peca peca_id) {
        this.peca_id = peca_id;
    }

    public Programa getPrograma_id() {
        return programa_id;
    }

    public void setPrograma_id(Programa programa_id) {
        this.programa_id = programa_id;
    }

    public Double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }



}
