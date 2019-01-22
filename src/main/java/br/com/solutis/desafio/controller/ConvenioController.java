package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Convenio;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("ConvenioController")
@RequestMapping("/convenio")
public class ConvenioController {


    @Autowired
    ConvenioService convenioService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Convenio bean) {
        return this.buildResponse(convenioService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //convenioService.getList(filterData.getPage());
        //convenioService.select(filterData);

        return this.buildResponse( convenioService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(convenioService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         convenioService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
