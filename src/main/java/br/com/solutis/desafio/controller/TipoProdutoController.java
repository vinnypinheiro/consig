package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.TipoProduto;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("TipoProdutoController")
@RequestMapping("/tipoproduto")
public class TipoProdutoController {


    @Autowired
    TipoProdutoService tipoprodutoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody TipoProduto bean) {
        return this.buildResponse(tipoprodutoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //tipoprodutoService.getList(filterData.getPage());
        //tipoprodutoService.select(filterData);

        return this.buildResponse( tipoprodutoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(tipoprodutoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         tipoprodutoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
