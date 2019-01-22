package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Agencia;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AgenciaController")
@RequestMapping("/agencia")
public class AgenciaController {


    @Autowired
    AgenciaService agenciaService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Agencia bean) {
        return this.buildResponse(agenciaService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //agenciaService.getList(filterData.getPage());
        //agenciaService.select(filterData);

        return this.buildResponse( agenciaService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(agenciaService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         agenciaService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
