package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.Auxilio;
import br.com.solutis.desafio.domain.Lancamento;
import br.com.solutis.desafio.domain.Mensalidade;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.LancamentoService;
import br.com.solutis.desafio.service.MensalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController("LancamentoController")
@RequestMapping("/lancamento")
public class lancamentoController {


    @Autowired
    LancamentoService lancamentoService;

    @Autowired
    MensalidadeService mensalidadeService;

    @Autowired
    private NamedParameterJdbcTemplate sqlDao;

    private final String getCars_sql = "select * from auxilio";



    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Lancamento bean) {



        return this.buildResponse(lancamentoService.save(bean));
    }



    @RequestMapping(value = "/", method = RequestMethod.POST)
    public  List<Auxilio> query( ) {


        List<Auxilio> auxilios = findAuxilos();

        return auxilios;


    }

    @Transactional
    public List<Auxilio> findAuxilos()
    {

        List<Auxilio> foundObjs = sqlDao.query(getCars_sql,
                (rs) -> {
                    List<Auxilio> retVal = new ArrayList<Auxilio>();
                    if (rs != null)
                    {


                    }

                    return  retVal;
                });

        return foundObjs;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(lancamentoService.getByIdCustom(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         lancamentoService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
