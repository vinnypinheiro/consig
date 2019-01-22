package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.*;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("VeiculoController")
@RequestMapping("/veiculo")
public class VeiculoController {


    @Autowired
    VeiculoService veiculoService;

    @Autowired
    ProgramaService programaService;

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
    public ResponseEntity save(@RequestBody Veiculo bean) {

        for (Programa p :bean.getProgramalist() ){
            p.setVeiculo_id(bean);
            programaService.save(p);
        }

        for (Endereco e :bean.getEnderecoList() ){
            e.setVeiculo_id(bean);
            enderecoService.save(e);
        }

        for (Contato e :bean.getContatoList() ){
            e.setVeiculo_id(bean);
            contatoService.save(e);
        }

        for (Telefone e :bean.getTelefoneList() ){
            e.setVeiculo_id(bean);
            telefoneService.save(e);
        }

        for (Email e :bean.getEmailList()){
            e.setVeiculo_id(bean);
            emailService.save(e);
        }

        for (Site e :bean.getSiteList()){
            e.setVeiculo_id(bean);
            siteService.save(e);
        }

        for(ContaBancaria e :bean.getContaBancariaList()){
            e.setVeiculo_id(bean);
            contaBancariaService.save(e);
        }

        return this.buildResponse(veiculoService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //veiculoService.getList(filterData.getPage());
        //veiculoService.select(filterData);

        return this.buildResponse( veiculoService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(veiculoService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         veiculoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
