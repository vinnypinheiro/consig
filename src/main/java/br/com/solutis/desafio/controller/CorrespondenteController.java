package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Associado;
import br.com.solutis.desafio.domain.Correspondente;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.AssociadoService;
import br.com.solutis.desafio.service.CorrespondenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CorrespondenteController")
@RequestMapping("/correspondente")
public class CorrespondenteController {


    @Autowired
    CorrespondenteService correspondenteService;

    @Autowired
    AssociadoService associadoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Correspondente bean) {


        return this.buildResponse(correspondenteService.save(bean));


    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //correspondenteService.getList(filterData.getPage());
        //correspondenteService.select(filterData);

        return this.buildResponse( correspondenteService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(correspondenteService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         correspondenteService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
