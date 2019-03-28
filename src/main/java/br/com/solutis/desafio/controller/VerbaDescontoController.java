package br.com.solutis.desafio.controller;

import br.com.solutis.desafio.domain.VerbaDesconto;
import br.com.solutis.desafio.domain.Parcela;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.VerbaDescontoService;
import br.com.solutis.desafio.service.ParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("VerbaDescontoController")
@RequestMapping("/verbadesconto")
public class VerbaDescontoController {


    @Autowired
    VerbaDescontoService verbadescontoService;

    @Autowired
    ParcelaService parcelaService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody VerbaDesconto bean) {

        return this.buildResponse(verbadescontoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //verbadescontoService.getList(filterData.getPage());
        //verbadescontoService.select(filterData);

        return this.buildResponse( verbadescontoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(verbadescontoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         verbadescontoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
