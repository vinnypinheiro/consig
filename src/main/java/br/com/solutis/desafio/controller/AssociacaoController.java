package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Associacao;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.AssociacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AssociacaoController")
@RequestMapping("/associacao")
public class AssociacaoController {


    @Autowired
    AssociacaoService associacaoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Associacao bean) {
        return this.buildResponse(associacaoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //associacaoService.getList(filterData.getPage());
        //associacaoService.select(filterData);

        return this.buildResponse( associacaoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(associacaoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         associacaoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
