package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Uf;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("UfController")
@RequestMapping("/uf")
public class UfController {


    @Autowired
    UfService ufService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Uf bean) {
        return this.buildResponse(ufService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //ufService.getList(filterData.getPage());
        //ufService.select(filterData);

        return this.buildResponse( ufService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(ufService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         ufService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
