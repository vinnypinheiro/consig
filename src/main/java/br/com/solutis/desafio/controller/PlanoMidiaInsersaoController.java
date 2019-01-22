package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.PlanoMidiaInsersao;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.PlanoMidiaInsersaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("PlanoMidiaInsersaoController")
@RequestMapping("/planomidiainsersao")
public class PlanoMidiaInsersaoController {


    @Autowired
    PlanoMidiaInsersaoService planomidiainsersaoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody PlanoMidiaInsersao bean) {
        return this.buildResponse(planomidiainsersaoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //planomidiainsersaoService.getList(filterData.getPage());
        //planomidiainsersaoService.select(filterData);

        return this.buildResponse( planomidiainsersaoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(planomidiainsersaoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         planomidiainsersaoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
