package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.FaturamentoPadrao;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.FaturamentoPadraoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("FaturamentoPadraoController")
@RequestMapping("/faturamentopadrao")
public class FaturamentoPadraoController {


    @Autowired
    FaturamentoPadraoService faturamentopadraoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody FaturamentoPadrao bean) {
        return this.buildResponse(faturamentopadraoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //faturamentopadraoService.getList(filterData.getPage());
        //faturamentopadraoService.select(filterData);

        return this.buildResponse( faturamentopadraoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(faturamentopadraoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         faturamentopadraoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
