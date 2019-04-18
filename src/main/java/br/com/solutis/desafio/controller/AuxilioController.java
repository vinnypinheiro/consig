package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.*;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController("AuxilioController")
@RequestMapping("/auxilio")
public class AuxilioController {


    @Autowired
    AuxilioService auxilioService;

    @Autowired
    ParcelaService parcelaService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Auxilio bean) {

        LocalDate dataContrato = bean.getDataContrato();


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


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         auxilioService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
