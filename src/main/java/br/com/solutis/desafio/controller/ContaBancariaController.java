package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.ContaBancaria;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("ContaBancariaController")
@RequestMapping("/contabancaria")
public class ContaBancariaController {


    @Autowired
    ContaBancariaService contaBancariaService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody ContaBancaria bean) {
        return this.buildResponse(contaBancariaService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //ufService.getList(filterData.getPage());
        //ufService.select(filterData);

        return this.buildResponse( contaBancariaService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(contaBancariaService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

        contaBancariaService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
