package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Balanco;
import br.com.solutis.desafio.helper.filter.BalancoFilter;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.BalancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("BalancoController")
@RequestMapping("/balanco")
public class BalancoController {


    @Autowired
    BalancoService balancoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Balanco bean) {
        return this.buildResponse(balancoService.save(bean));
    }

    @RequestMapping(value = "/getbalanco", method = RequestMethod.POST)
    public ResponseEntity getBalanco(@RequestBody BalancoFilter filter) {
        return this.buildResponse(balancoService.getBalanco(filter));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        return this.buildResponse( balancoService.getList(filterData.getPage()));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(balancoService.getById(id));

    }

    @RequestMapping(value = "getview/{id}", method = RequestMethod.GET)
    public ResponseEntity getview(@PathVariable("id") String id) {

        Long idLong = Long.valueOf(id);

        return this.buildResponse(balancoService.getBalancoView(idLong));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         balancoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
