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

    public ResponseEntity saveFromEspelho(@RequestBody Auxilio bean) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEspelho = LocalDate.parse("03/04/2019", formatter);
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
