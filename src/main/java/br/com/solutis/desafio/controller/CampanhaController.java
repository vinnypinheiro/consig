package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Campanha;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CampanhaController")
@RequestMapping("/campanha")
public class CampanhaController {


    @Autowired
    CampanhaService campanhaService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Campanha bean) {
        return this.buildResponse(campanhaService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //campanhaService.getList(filterData.getPage());
        //campanhaService.select(filterData);

        return this.buildResponse( campanhaService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(campanhaService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         campanhaService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
