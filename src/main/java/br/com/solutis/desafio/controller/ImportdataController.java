package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.*;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



@RestController("ImportdataController")
@RequestMapping("/importdata")
public class ImportdataController {


    @Autowired
    ImpostdataService importdataService;

    @Autowired
    AssociadoService associadoService;

    @Autowired
    AuxilioService auxilioService;

    @Autowired
    AssociacaoService associacaoService;

    @Autowired
    CorrespondenteService correspondenteService;

    @Autowired
    ConvenioService convenioService;

    @Autowired
    VerbaDescontoService verbaDescontoService;

    @Autowired
    ParcelaService parcelaService;

    long time = 0;
    int line = 2;
    int inseridos = 0;
    int minline = 0;
    int maxline = 99999999;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Importdata bean) {
        return this.buildResponse(importdataService.save(bean));
    }

    @RequestMapping(value = "/importcsv", method = RequestMethod.POST)
    public ResponseEntity importcsv() throws FileNotFoundException {


        BufferedReader br = null;
        FileReader fl =  new FileReader("X:\\DOCUMENTOS\\projetos\\DAIANE\\iesba.CSV");


        try {
            //le o csv
            br = new BufferedReader(fl);

            String linha;
            linha = br.readLine();


            //pra cada linha
            while ((linha = br.readLine()) != null && line <= maxline ) {
                if(line >= minline){
                    //divide a lina em campos informando o separador
                    String[] campos = linha.split(";");

                    //faz alguma coisa com os campos
                    if(!campos[5].trim().isEmpty()){
                        makeAssociado(campos);
                    }

                }
                line++;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("200");
    }

    public void makeAssociado(String[] campos ) throws ParseException {


        Associacao associacao = associacaoService.getById( Long.valueOf(2));
        Correspondente correspondente = correspondenteService.getById(Long.valueOf(233));
        Convenio convenio = convenioService.getById(Long.valueOf(1));
        VerbaDesconto verbaDesconto = verbaDescontoService.getById(Long.valueOf(2));

        System.out.println("##########################     "+line+" - "+(line - (inseridos +1)));

        Associado associado = new Associado();

        if(campos[5] != null && !campos[5].isEmpty()){
            String cpfString = campos[5].replaceAll("[.-]", "").trim();

            associado.setCpf( Long.valueOf(cpfString));
        }


        associado.setNome(campos[6]);


        associadoService.save(associado);

        String dataReserva = campos[9];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTimeReserva = LocalDate.parse(dataReserva, formatter);

         LocalDate dataContrato = LocalDate.now();
         String numeroproposta;

         //Valores
         Double vlrauxilio = Double.valueOf(campos[0].replace(".","").replace(",","."));
         Double vlrParcela = Double.valueOf(campos[2].replace(".","").replace(",","."));
         Double vlrtotal =Double.valueOf(campos[4].replace(".","").replace(",","."));

         Integer qtdparcelas =  Integer.parseInt(campos[8]);
         Integer porcentagem;

        Auxilio auxilio = new Auxilio();
        auxilio.setAssociado_id(associado);
        auxilio.setDatareserva(dateTimeReserva);
        auxilio.setDataContrato(dateTimeReserva);
        auxilio.setVlrauxilio(vlrauxilio);
        auxilio.setVlrparcelas(vlrParcela);
        auxilio.setQtdparcelas(qtdparcelas);
        auxilio.setVlrtotal(vlrtotal);
        auxilio.setTipo("CONTRATO");

        //Relacionamentos
        auxilio.setAssociacao_id(associacao);
        auxilio.setCorrespondente_id(correspondente);
        auxilio.setConvenio_id(convenio);
        auxilio.setVerbadesconto_id(verbaDesconto);

        auxilioService.save(auxilio);


        for (int i=0; i < qtdparcelas ; i++ ){

            Parcela parcela = new Parcela();
            parcela.setParcela(i+1);
            parcela.setData(dateTimeReserva.plusMonths(i+1));
            parcela.setStatus("EM ABERTO");
            parcela.setAuxilio_id(auxilio);

            parcela.setValor(auxilio.getVlrparcelas());

            parcelaService.save(parcela);
        }

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody FilterData filterData) {

        //importdataService.getList(filterData.getPage());
        //importdataService.select(filterData);

        return this.buildResponse( importdataService.getList(filterData.getPage()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {

        return this.buildResponse(importdataService.getById(id));

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {

         importdataService.delete(Long.valueOf(id));

    }

    protected <T> ResponseEntity<T> buildResponse(T value) {
        return new ResponseEntity<T>(value, (value != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
