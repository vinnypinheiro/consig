package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.FormatoRevista;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.FormatoRevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("FormatoRevistaController")
@RequestMapping("/formatorevista")
public class FormatoRevistaController {


    @Autowired
    FormatoRevistaService formatorevistaService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody FormatoRevista bean) {
        return this.buildResponse(formatorevistaService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //formatorevistaService.getList(filterData.getPage());
        //formatorevistaService.select(filterData);

        return this.buildResponse( formatorevistaService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(formatorevistaService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         formatorevistaService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
