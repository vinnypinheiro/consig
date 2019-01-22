package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.PedidoInsercaoItem;
import br.com.solutis.desafio.domain.PlanoMidiaInsersaoItem;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.PedidoInsercaoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("PedidoInsercaoItemController")
@RequestMapping("/pedidoinsercaoitem")
public class PedidoInsercaoItemController {


    @Autowired
    PedidoInsercaoItemService pedidoinsercaoitemService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody PedidoInsercaoItem bean) {

        ResponseEntity<PedidoInsercaoItem> teste;

        teste = this.buildResponse(pedidoinsercaoitemService.save(bean));

        return teste;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //pedidoinsercaoitemService.getList(filterData.getPage());
        //pedidoinsercaoitemService.select(filterData);

        return this.buildResponse( pedidoinsercaoitemService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(pedidoinsercaoitemService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         pedidoinsercaoitemService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
