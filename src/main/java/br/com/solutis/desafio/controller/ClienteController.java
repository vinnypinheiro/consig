package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.*;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("ClienteController")
@RequestMapping("/cliente")
public class ClienteController {


    @Autowired
    ClienteService clienteService;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    ContatoService contatoService;

    @Autowired
    TelefoneService telefoneService;

    @Autowired
    EmailService emailService;

    @Autowired
    SiteService siteService;

    @Autowired
    ContaBancariaService contaBancariaService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Cliente bean) {

      for (Endereco e :bean.getEnderecoList() ){
           e.setCliente_id(bean);
            enderecoService.save(e);
        }

        for (Contato e :bean.getContatoList() ){
            e.setCliente_id(bean);
            contatoService.save(e);
        }

        for (Telefone e :bean.getTelefoneList() ){
            e.setCliente_id(bean);
            telefoneService.save(e);
        }

        for (Email e :bean.getEmailList()){
          e.setCliente_id(bean);
          emailService.save(e);
        }

        for (Site e :bean.getSiteList()){
          e.setCliente_id(bean);
          siteService.save(e);
        }

        for(ContaBancaria e :bean.getContaBancariaList()){
          e.setCliente_id(bean);
          contaBancariaService.save(e);
        }


        return this.buildResponse(clienteService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //clienteService.getList(filterData.getPage());
        //clienteService.select(filterData);

        return this.buildResponse( clienteService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(clienteService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         clienteService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
