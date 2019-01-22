package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Produto;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("ProdutoController")
@RequestMapping("/produto")
public class ProdutoController {


    @Autowired
    ProdutoService produtoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Produto bean) {
        return this.buildResponse(produtoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //produtoService.getList(filterData.getPage());
        //produtoService.select(filterData);

        return this.buildResponse( produtoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(produtoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         produtoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
