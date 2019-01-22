package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.MailPi;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.MailPiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("MailPiController")
@RequestMapping("/mailpi")
public class MailPiController {


    @Autowired
    MailPiService mailpiService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody MailPi bean) {
        return this.buildResponse(mailpiService.save(bean));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //mailpiService.getList(filterData.getPage());
        //mailpiService.select(filterData);

        return this.buildResponse( mailpiService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(mailpiService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         mailpiService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
