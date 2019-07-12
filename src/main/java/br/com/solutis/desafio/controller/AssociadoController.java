package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Associado;
import br.com.solutis.desafio.domain.Mensalidade;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.Filtro;
import br.com.solutis.desafio.service.AssociadoService;
import br.com.solutis.desafio.service.MensalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AssociadoController")
@RequestMapping("/associado")
public class AssociadoController {


    @Autowired
    AssociadoService associadoService;

    @Autowired
    MensalidadeService mensalidadeService;

    @RequestMapping(value = "/busca", method = RequestMethod.POST)
    public ResponseEntity queryBusca(@RequestBody Filtro filtro ) {

        return this.buildResponse( associadoService.getAssociadoByCrit(filtro));
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Associado bean) {


        return this.buildResponse(associadoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //associadoService.getList(filterData.getPage());
        //associadoService.select(filterData);

        return this.buildResponse( associadoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(associadoService.getByIdCustom(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         associadoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
