package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.PlanoMidiaInsersaoItem;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.PlanoMidiaInsersaoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("PlanoMidiaInsersaoItemController")
@RequestMapping("/planomidiainsersaoitem")
public class PlanoMidiaInsersaoItemController {


    @Autowired
    PlanoMidiaInsersaoItemService planomidiainsersaoitemService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody PlanoMidiaInsersaoItem bean) {


        return this.buildResponse(planomidiainsersaoitemService.save(bean));

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //planomidiainsersaoitemService.getList(filterData.getPage());
        //planomidiainsersaoitemService.select(filterData);

        return this.buildResponse( planomidiainsersaoitemService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(planomidiainsersaoitemService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         planomidiainsersaoitemService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
