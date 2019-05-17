package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Corretor;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.AssociadoService;
import br.com.solutis.desafio.service.CorretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CorretorController")
@RequestMapping("/corretor")
public class CorretorController {


    @Autowired
    CorretorService corretorService;

    @Autowired
    AssociadoService associadoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Corretor bean) {


        return this.buildResponse(corretorService.save(bean));


    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //corretorService.getList(filterData.getPage());
        //corretorService.select(filterData);

        return this.buildResponse( corretorService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(corretorService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         corretorService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
