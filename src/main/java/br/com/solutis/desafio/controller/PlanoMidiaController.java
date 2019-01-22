package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Peca;
import br.com.solutis.desafio.domain.PedidoInsercaoItem;
import br.com.solutis.desafio.domain.PlanoMidia;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.PecaService;
import br.com.solutis.desafio.service.PedidoInsercaoItemService;
import br.com.solutis.desafio.service.PlanoMidiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("PlanoMidiaController")
@RequestMapping("/planomidia")
public class PlanoMidiaController {


    @Autowired
    PlanoMidiaService planomidiaService;

    @Autowired
    PecaService pecaService;

    @Autowired
    PedidoInsercaoItemService PedidoInsercaoItemService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody PlanoMidia bean) {

        for (Peca p :bean.getPecalist() ){
            p.setPlanomidia_id(bean);
            pecaService.save(p);
        }

        for (PedidoInsercaoItem pii :bean.getPedidoDeInsercaoItemlist() ) {
            pii.setPlanomidia_id(bean);
            PedidoInsercaoItemService.save(pii);

        }

        return this.buildResponse(planomidiaService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //planomidiaService.getList(filterData.getPage());
        //planomidiaService.select(filterData);

        return this.buildResponse( planomidiaService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

    ResponseEntity<PlanoMidia> teste;

       teste = this.buildResponse(planomidiaService.getById(id));

       return teste;

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         planomidiaService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
