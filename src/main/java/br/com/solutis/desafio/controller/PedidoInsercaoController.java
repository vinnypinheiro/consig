package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.PedidoInsercao;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.PedidoInsercaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("PedidoInsercaoController")
@RequestMapping("/pedidoinsercao")
public class PedidoInsercaoController {


    @Autowired
    PedidoInsercaoService pedidoinsercaoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody PedidoInsercao bean) {
        return this.buildResponse(pedidoinsercaoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //pedidoinsercaoService.getList(filterData.getPage());
        //pedidoinsercaoService.select(filterData);

        return this.buildResponse( pedidoinsercaoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(pedidoinsercaoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         pedidoinsercaoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
