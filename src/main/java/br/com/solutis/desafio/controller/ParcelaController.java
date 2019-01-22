package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Parcela;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.ParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("ParcelaController")
@RequestMapping("/parcela")
public class ParcelaController {


    @Autowired
    ParcelaService parcelaService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Parcela bean) {

        if (bean.getId() > 0){
            bean.setId(bean.getId());
        }

        return this.buildResponse(parcelaService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //parcelaService.getList(filterData.getPage());
        //parcelaService.select(filterData);

        return this.buildResponse( parcelaService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(parcelaService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         parcelaService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
