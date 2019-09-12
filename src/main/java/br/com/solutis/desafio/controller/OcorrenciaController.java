package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Ocorrencia;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController("OcorrenciaController")
@RequestMapping("/ocorrencia")
public class OcorrenciaController {


    @Autowired
    OcorrenciaService ocorrenciaService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Ocorrencia bean) {

        bean.setData(LocalDate.now());

        return this.buildResponse(ocorrenciaService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //ocorrenciaService.getList(filterData.getPage());
        //ocorrenciaService.select(filterData);

        return this.buildResponse( ocorrenciaService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(ocorrenciaService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         ocorrenciaService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
