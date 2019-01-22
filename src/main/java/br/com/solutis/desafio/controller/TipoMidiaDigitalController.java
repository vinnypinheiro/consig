package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.TipoMidiaDigital;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.TipoMidiaDigitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("TipoMidiaDigitalController")
@RequestMapping("/tipomidiadigital")
public class TipoMidiaDigitalController {


    @Autowired
    TipoMidiaDigitalService tipomidiadigitalService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody TipoMidiaDigital bean) {
        return this.buildResponse(tipomidiadigitalService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //tipomidiadigitalService.getList(filterData.getPage());
        //tipomidiadigitalService.select(filterData);

        return this.buildResponse( tipomidiadigitalService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(tipomidiadigitalService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         tipomidiadigitalService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
