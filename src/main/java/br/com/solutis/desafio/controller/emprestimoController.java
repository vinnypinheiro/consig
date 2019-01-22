package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Emprestimo;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("EmprestimoController")
@RequestMapping("/emprestimo")
public class emprestimoController {


    @Autowired
    EmprestimoService emprestimoService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Emprestimo bean) {
        return this.buildResponse(emprestimoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //emprestimoService.getList(filterData.getPage());
        //emprestimoService.select(filterData);

        return this.buildResponse( emprestimoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(emprestimoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         emprestimoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
