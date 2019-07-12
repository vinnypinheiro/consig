package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.*;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController("AuxilioController")
@RequestMapping("/auxilio")
public class AuxilioController {


    @Autowired
    AuxilioService auxilioService;

    @Autowired
    ParcelaService parcelaService;

    @Autowired
    MensalidadeService mensalidadeService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Auxilio bean) {

        LocalDate dataContrato = bean.getDataContrato();

        bean.setQtdparcelaspagas(0);
        bean.setQtdparcelasnaopagas(bean.getQtdparcelas());

        for (int i=0; i < bean.getQtdparcelas() ; i++ ){

            Parcela p = new Parcela();
            p.setParcela(i+1);
            p.setStatus("Em Aberto");
            p.setAuxilio_id(bean);
            p.setData(bean.getDataContrato());
            p.setDatavencimento(dataContrato.plusMonths(i+1).withDayOfMonth(4));
            p.setValor(bean.getVlrparcelas());


            parcelaService.save(p);
        }

        //cria as mensalidades
        for (int i=0; i < bean.getQtdparcelas() ; i++ ){

            Mensalidade m = new Mensalidade();
            m.setMensalidade(i+1);
            m.setStatuspagamento("Em Aberto");
            m.setAssociado_id(bean.getAssociado_id());
            m.setDatavencimento(dataContrato.plusMonths(i+1).withDayOfMonth(3));
            m.setVlrmensalidade( bean.getAssociado_id().getVlrmensalidade() );

            mensalidadeService.save(m);
        }

        return this.buildResponse(auxilioService.save(bean));
    }

    @RequestMapping(value = "/quitarparcelas/{qtd}", method = RequestMethod.POST)
    public ResponseEntity quitarparcelas(@PathVariable("qtd") Integer qtd, @RequestBody Auxilio bean) {

        Auxilio auxilioUpdate = auxilioService.getById(bean.getId());

        List<Parcela> parcelas = bean.getParcelaList();

        for(Parcela parcela : parcelas){

            if (parcela.getParcela() <= qtd){

                parcela.setValorpago(parcela.getValor());
                parcela.setDatapagamento(parcela.getDatavencimento());
                parcela.setStatus("LIQUIDADA");

                parcelaService.save(parcela);

            }



        }

        auxilioUpdate.setQtdparcelaspagas(qtd);
        auxilioUpdate.setQtdparcelasnaopagas(auxilioUpdate.getQtdparcelas() - qtd);
        auxilioUpdate.setTotalpago(auxilioUpdate.getVlrparcelas() * qtd);

        if (auxilioUpdate.getQtdparcelas() == qtd){
            auxilioUpdate.setStatus("QUITADO");
        }

        return this.buildResponse(auxilioService.save(auxilioUpdate));
    }


    @RequestMapping(value = "/refinanciar/{id}", method = RequestMethod.POST)
    public ResponseEntity refinanciar(@PathVariable("id") Long id, @RequestBody Auxilio bean) {

        Auxilio auxilioRefi = auxilioService.getById(id);

        List<Parcela> parcelasRef = auxilioRefi.getParcelaList();


        for(Parcela parcela : parcelasRef){

            if (parcela.getStatus() == "Em Aberto"){

                parcela.setValorpago(parcela.getValor());
                parcela.setDatapagamento(bean.getDataContrato());
                parcela.setStatus("LIQUIDADA REF.");

                parcelaService.save(parcela);

            }



        }

        auxilioRefi.setQtdparcelaspagas(auxilioRefi.getQtdparcelas());
        auxilioRefi.setQtdparcelasnaopagas(0);
        auxilioRefi.setVlrliquidadorefi(bean.getVlrliquidadorefi());
        auxilioRefi.setTotalpago(auxilioRefi.getTotalpago() + bean.getVlrliquidadorefi());
        auxilioRefi.setStatus("QUITADO");
        auxilioRefi.setOplock("beta refis");

        auxilioService.save(auxilioRefi);


        LocalDate dataContrato = bean.getDataContrato();

        bean.setQtdparcelaspagas(0);
        bean.setDataContrato(dataContrato);
        bean.setVlrliquidadorefi(0.0);
        bean.setQtdparcelasnaopagas(bean.getQtdparcelas());
        bean.setTipo("REFINANCIAMENTO");
        bean.setTotalpago(0.0);
        bean.setTotalaberto(bean.getVlrparcelas() * bean.getQtdparcelas());
        bean.setOplock("beta refis");

        for (int i=0; i < bean.getQtdparcelas() ; i++ ){

            Parcela p = new Parcela();
            p.setParcela(i+1);
            p.setStatus("Em Aberto");
            p.setAuxilio_id(bean);
            p.setData(bean.getDataContrato());
            p.setDatavencimento(dataContrato.plusMonths(i+1).withDayOfMonth(4));
            p.setValor(bean.getVlrparcelas());


            parcelaService.save(p);
        }

        //cria as mensalidades
        for (int i=0; i < bean.getQtdparcelas() ; i++ ){

            Mensalidade m = new Mensalidade();
            m.setMensalidade(i+1);
            m.setStatuspagamento("Em Aberto");
            m.setAssociado_id(bean.getAssociado_id());
            m.setDatavencimento(dataContrato.plusMonths(i+1).withDayOfMonth(3));
            m.setVlrmensalidade( bean.getAssociado_id().getVlrmensalidade() );

            mensalidadeService.save(m);
        }

        return this.buildResponse(auxilioService.save(bean));

    }

    public ResponseEntity saveFromEspelho(@RequestBody Auxilio bean) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEspelho = LocalDate.parse("03/05/2019", formatter);
        LocalDate dataContrato = dataEspelho.minusMonths(bean.getQtdparcelaspagas());

        bean.setQtdparcelaspagas(0);
        bean.setQtdparcelasnaopagas(bean.getQtdparcelas());

        for (int i=0; i < bean.getQtdparcelas() ; i++ ){

            Parcela p = new Parcela();
            p.setParcela(i+1);
            p.setStatus("EM ABERTO");
            p.setAuxilio_id(bean);
            p.setData(dataContrato);
            p.setDatavencimento(dataContrato.plusMonths(i+1).withDayOfMonth(4));
            p.setValor(bean.getVlrparcelas());
            p.setOplock("7777");


            parcelaService.save(p);
        }

//        //cria as mensalidades
//        for (int i=0; i < bean.getQtdparcelas() ; i++ ){
//
//            Mensalidade m = new Mensalidade();
//            m.setMensalidade(i+1);
//            m.setStatuspagamento("EM ABERTO");
//            m.setAssociado_id(bean.getAssociado_id());
//            m.setDatavencimento(dataContrato);
//            m.setVlrmensalidade( bean.getAssociado_id().getVlrmensalidade() );
//
//            mensalidadeService.save(m);
//        }

        return this.buildResponse(auxilioService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //auxilioService.getList(filterData.getPage());
        //auxilioService.select(filterData);

        return this.buildResponse( auxilioService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(auxilioService.getById(id));

    }

    public ResponseEntity findByIdEspelho(@PathVariable("id") Long id) {


        return this.buildResponse(auxilioService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         auxilioService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
