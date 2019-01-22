package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Endereco;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("EnderecoController")
@RequestMapping("/endereco")
public class EnderecoController {


    @Autowired
    EnderecoService enderecoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Endereco bean) {
        return this.buildResponse(enderecoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //ufService.getList(filterData.getPage());
        //ufService.select(filterData);

        return this.buildResponse( enderecoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(enderecoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

        enderecoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
