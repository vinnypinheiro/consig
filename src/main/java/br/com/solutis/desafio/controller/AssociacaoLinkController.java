package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.AssociacaoLink;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.AssociacaoLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AssociacaoLinkController")
@RequestMapping("/associacaoLink")
public class AssociacaoLinkController {


    @Autowired
    AssociacaoLinkService associacaoLinkService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody AssociacaoLink bean) {
        return this.buildResponse(associacaoLinkService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //associacaoLinkService.getList(filterData.getPage());
        //associacaoLinkService.select(filterData);

        return this.buildResponse( associacaoLinkService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(associacaoLinkService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         associacaoLinkService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
