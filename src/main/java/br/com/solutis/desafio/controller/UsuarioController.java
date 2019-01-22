package br.com.solutis.desafio.controller;


import br.com.solutis.desafio.domain.Usuario;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("UsuarioController")
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    UsuarioService usuarioService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Usuario bean) {
        return this.buildResponse(usuarioService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //usuarioService.getList(filterData.getPage());
        //usuarioService.select(filterData);

        return this.buildResponse( usuarioService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(usuarioService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         usuarioService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
