package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.ContaReceber;
import br.com.solutis.desafio.helper.filter.BalancoFilter;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.ContaReceberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ContaReceberController")
@RequestMapping("/contaReceber")
public class ContaReceberController {


    @Autowired
    ContaReceberService contaReceberService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody ContaReceber bean) {
        return this.buildResponse(contaReceberService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //contaReceberService.getList(filterData.getPage());
        //contaReceberService.select(filterData);

        return this.buildResponse( contaReceberService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/getcontasreceber", method = RequestMethod.POST)
    public List<ContaReceber> getBalanco(@RequestBody BalancoFilter filter) {
        return contaReceberService.getContasReceber(filter);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(contaReceberService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         contaReceberService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
