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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@RestController("EspelhoRetronoController")
@RequestMapping("/espelhoretorno")
public class EspelhoRetornoController {


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

    @Autowired
    MensalidadeService mensalidadeService;

    long time = 0;
    int line = 2;
    int inseridos = 0;
    int minline = 0;
    int maxline = 99999999;

    String oplock = "1993";


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Importdata bean) {
        return this.buildResponse(importdataService.save(bean));
    }

    @RequestMapping(value = "/importar", method = RequestMethod.POST)
    public ResponseEntity importcsv() throws FileNotFoundException {


        BufferedReader br = null;
        FileReader fl =  new FileReader("X:\\DOCUMENTOS\\projetos\\DAIANE\\EspelhoRemessaSuprev.CSV");


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
                    if(!campos[1].trim().isEmpty()){
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

        System.out.println("##########################"+line+" - "+(line - (inseridos +1)));


        if(campos[1] != null && !campos[1].isEmpty()){
            String cpfString = campos[1].replaceAll("[.-]", "").trim();

            Associado associado = associadoService.getByCpf(Long.valueOf(cpfString));
            LocalDate dateTimeOcorrencia = LocalDate.now();
            Ocorrencia ocorrencia = new Ocorrencia();
            ocorrencia.setAssociado_id(associado);
            ocorrencia.setData(dateTimeOcorrencia);
            ocorrencia.setDescricao("Leitura espelho retorno oplock = "+ oplock );

            // Atualiza o associado
            associado.setOrgao(campos[3]);
            associado.setMatricula(campos[2]);
            associado.setSituacao(campos[8]);



            // Atualiza a mensalidade
            if(campos[10].contains("5022")){
                atualizaMensalidade(associado, campos);
            }

            // Atualiza a Parcela
            if(campos[10].contains("5041")){
                atualizaParcela(associado, campos);
            }



        }

    }


    public void atualizaParcela(Associado associado,String[] campos  ) throws ParseException {

        List<Auxilio> auxilios = new ArrayList<>( associado.getAuxilioList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Double vlrparcela = Double.valueOf(campos[7].replace(".","").replace(",","."));
        Double vlrpago = Double.valueOf(campos[7].replace(".","").replace(",","."));
        int parcelaNum = Integer.valueOf( campos[5].substring(0, 2));

        for(Auxilio auxilio : auxilios){

            int retval = Double.compare(auxilio.getVlrparcelas(), vlrparcela);

            if( retval == 0  ){

                List<Parcela> parcelaList = new ArrayList<>(auxilio.getParcelaList());

                Comparator<Parcela> byParcela = (e1, e2) -> Integer.compare(e2.getParcela(), e1.getParcela());
                parcelaList.sort(byParcela.reversed());

                for(Parcela parcela : parcelaList){

                    if (parcela.getParcela() > parcelaNum ) return;

                         parcela.setStatus("PAGO");
                         parcela.setValorpago(vlrpago);
                        parcela.setDatapagamento(parcela.getDatavencimento());
                        parcela.setOplock(oplock);

                    parcelaService.save(parcela);

                }




            } else {


                List<Parcela> parcelaList = new ArrayList<>(auxilio.getParcelaList());
                int i = 0;
                for(Parcela parcela : parcelaList){
                    i++;
                    if(parcela.getDatavencimento() != null ){
                        parcela.setDatapagamento(parcela.getDatavencimento());
                    }else{
                        parcela.setDatapagamento(auxilio.getDatareserva().plusMonths(i+1).withDayOfMonth(3));
                    }
                    parcela.setStatus("PAGO");
                    parcela.setOplock(oplock);

                    parcelaService.save(parcela);

                }

            }



        }


        return;

    }







    public void atualizaMensalidade(Associado associado,String[] campos  ) throws ParseException {

        List<Mensalidade> mensalidades =   new ArrayList<>(associado.getMensalidadeList());
        Double vlrParcela = Double.valueOf(campos[6].replace(".","").replace(",","."));

        for(Mensalidade mensalidade : mensalidades){
            mensalidadeService.delete(mensalidade.getId());
        }

        int qtdMensalidadesPagas = Integer.valueOf( campos[5].substring(0, 2));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataReferencia = LocalDate.parse("03/04/2019", formatter);

        for (int i=0; i < qtdMensalidadesPagas ; i++ ){

            LocalDate dataPagamento = dataReferencia.minusMonths(i+1).withDayOfMonth(3);

            Mensalidade mensalidade = new Mensalidade();
            mensalidade.setMensalidade(dataPagamento.getMonthValue());
            mensalidade.setMesanoref(String.valueOf(dataPagamento.getYear()));
            mensalidade.setDatavencimento(dataPagamento);
            mensalidade.setDataprocesamento(dataPagamento);
            mensalidade.setStatuspagamento("PAGO");
            mensalidade.setAssociado_id(associado);
            mensalidade.setVlrmensalidade(vlrParcela);
            mensalidade.setOplock(oplock);

            mensalidadeService.save(mensalidade);

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
