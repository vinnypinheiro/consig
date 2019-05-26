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
import java.time.temporal.ChronoUnit;
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

    @Autowired
    EspelhoRetornoService espelhoRetornoService;

    @Autowired
    AuxilioController auxilioController;

    long time = 0;
    int line = 2;
    int inseridos = 0;
    int minline = 0;
    int maxline = 99999999;

    String oplock = "7777";


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Importdata bean) {
        return this.buildResponse(importdataService.save(bean));
    }

    @RequestMapping(value = "/importar", method = RequestMethod.POST)
    public ResponseEntity importcsv() throws FileNotFoundException {


        BufferedReader br = null;
        FileReader fl =  new FileReader("X:\\DOCUMENTOS\\projetos\\DAIANE\\remessa\\MapaFinanceiroSUPREVFamcred.CSV");


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

                    //faz alguma coisa com os campos de cada linha
                    if(!campos[0].trim().isEmpty()){
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
        
        String cpf = campos[2];
        String matricula = campos[3];
        String parcela = campos[6];
        Double vlrParcela = Double.valueOf(campos[10].replace(".","").replace(",","."));
        Double vlrDescontado = Double.valueOf(campos[11].replace(".","").replace(",","."));
        String contrato = campos[13];
        String situacao = campos[1];
        String orgao = campos[4];
        

        EspelhoRetorno espelhoRetorno = new EspelhoRetorno();
        espelhoRetorno.setMesreferencia(4);
        espelhoRetorno.setVlrParcela(vlrParcela);
        espelhoRetorno.setVlrDescontado(vlrDescontado);
        espelhoRetorno.setParcela(parcela);
        espelhoRetorno.setCpf(cpf);

        //Verificação de CPF
        if(cpf != null && !cpf.isEmpty()){

            //Seta CPF
            String cpfString = cpf.replaceAll("[.-]", "").trim();

            //adiciona 0 ao incio do CPF de 10 posições
            if(Long.valueOf(cpfString) == 10 ){
                cpfString = "0"+cpfString;
            }

            //Busca o Associado para atualizar o auxilio
            Associado associado = associadoService.getByCpf(Long.valueOf(cpfString));

            if(associado == null ){
                associado = new Associado();

                if(cpf != null && !cpf.isEmpty()){
                    associado.setNome(campos[0]);
                    associado.setCpf( Long.valueOf(cpfString) );
                    associado.setOplock(oplock);
                    associadoService.save(associado);

                }
            }

            espelhoRetorno.setAssociado_id(associado);


            //gera a ocorrencia
            LocalDate dateTimeOcorrencia = LocalDate.now();
            Ocorrencia ocorrencia = new Ocorrencia();
            ocorrencia.setAssociado_id(associado);
            ocorrencia.setData(dateTimeOcorrencia);
            ocorrencia.setDescricao("Leitura espelho retorno oplock = "+ oplock );

            // Atualiza o associado

            //matricula
           // if(matricula != null){
          //      associado.setMatricula(matricula);
         //   }else{
          //      associado.setMatricula("");
          //  }

            //situação
         //   if(situacao != null){
         //       associado.setSituacao(situacao);
         //   }else{
          //      associado.setSituacao("");
        //    }

            //orgão
         //   if(orgao != null){
         //       associado.setOrgao(orgao);
          //  }else{
         //       associado.setOrgao("");
         //   }

            // Atualiza a mensalidade
            // if(campos[10].contains("5022")){
            //    atualizaMensalidade(associado, campos);
           // }

            // Atualiza o Auxilio
            if(associado != null){
                atualizaAuxilio(associado, campos, espelhoRetorno);
            } else {

                espelhoRetornoService.save(espelhoRetorno);

            }



        }

    }


    public void atualizaAuxilio(Associado associado,String[] campos, EspelhoRetorno espelhoRetorno  ) throws ParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String situacao = campos[1];
        String cpf = campos[2];
        String matricula = campos[3];
        String orgao = campos[4];
        String situacaoBordero = campos[5];
        int parcelaNum = Integer.valueOf( campos[7]);
        int pagas = Integer.valueOf(campos[7]);
        int prazo = Integer.valueOf(campos[8]);
        int naopagas = Integer.valueOf(campos[9]);
        Double vlrParcela = Double.valueOf(campos[10].replace(".","").replace(",","."));
        Double vlrDescontado = Double.valueOf(campos[11].replace(".","").replace(",","."));
        String contrato = campos[13];


        try {
            

           /* Auxilio auxilio = new Auxilio();

            auxilio.setAssociado_id(associado);

            auxilio.setNumeroproposta(contrato);
            auxilio.setQtdparcelas(prazo);
            auxilio.setQtdparcelaspagas(pagas);
            auxilio.setQtdparcelasnaopagas(naopagas);
            auxilio.setVlrparcelas(vlrParcela);
            auxilio.setPorcentagem(4);
            auxilio.setOplock(oplock);

            auxilioController.saveFromEspelho(auxilio);*/

            List<Auxilio> auxilioList = associado.getAuxilioList();

            for(Auxilio aux : auxilioList){

                aux.setQtdparcelaspagas(pagas);
                aux.setQtdparcelasnaopagas(naopagas);

                if ( aux.getOplock() != null ){

                    List<Parcela> parcelaList = new ArrayList<>(aux.getParcelaList());

                    Comparator<Parcela> byParcela = (e1, e2) -> Integer.compare(e2.getParcela(), e1.getParcela());
                    parcelaList.sort(byParcela.reversed());

                    for(Parcela parcela : parcelaList){

                        if (parcela.getParcela() <= parcelaNum & vlrDescontado > 0 ){
                            parcela.setStatus("LIQUIDADA");
                            parcela.setValorpago(vlrDescontado);
                            parcela.setDatapagamento(parcela.getDatavencimento());
                            parcela.setOplock(oplock);

                            parcelaService.save(parcela);
                        }

                    }



                }

            }



            espelhoRetornoService.save(espelhoRetorno);



        } catch(Exception e){
            return;
        }


        return;

    }



    public void atualizaMensalidade(Associado associado,String[] campos  ) throws ParseException {



        List<Mensalidade> mensalidades =   new ArrayList<>(associado.getMensalidadeList());

        if (!campos[7].contains("-")){
            Double vlrParcela = Double.valueOf(campos[7].replace(".","").replace(",","."));

            if (vlrParcela > 0 ){


                for(Mensalidade mensalidade : mensalidades){
                    mensalidadeService.delete(mensalidade.getId());
                }

                int qtdMensalidadesPagas = Integer.valueOf(campos[3]);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataReferencia = LocalDate.parse("03/04/2019", formatter);

                for (int i=0; i < qtdMensalidadesPagas ; i++ ){

                    LocalDate dataPagamento = dataReferencia.minusMonths(i+1).withDayOfMonth(3);

                    Mensalidade mensalidade = new Mensalidade();
                    mensalidade.setMensalidade(qtdMensalidadesPagas - i);
                    mensalidade.setMesanoref(String.valueOf(dataPagamento.getYear()));
                    mensalidade.setDatavencimento(dataPagamento);
                    mensalidade.setDataprocesamento(dataPagamento);
                    mensalidade.setStatuspagamento("LIQUIDADA");
                    mensalidade.setAssociado_id(associado);
                    mensalidade.setVlrmensalidade(vlrParcela);
                    mensalidade.setOplock(oplock);

                    mensalidadeService.save(mensalidade);

                }

            } else {

                for(Mensalidade mensalidade : mensalidades){
                    mensalidadeService.delete(mensalidade.getId());
                }

                int qtdMensalidadesPagas = Integer.valueOf(campos[3]);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataReferencia = LocalDate.parse("03/04/2019", formatter);

                for (int i=0; i < qtdMensalidadesPagas ; i++ ){

                    LocalDate dataPagamento = dataReferencia.minusMonths(i+2).withDayOfMonth(3);

                    Mensalidade mensalidade = new Mensalidade();
                    mensalidade.setMensalidade(qtdMensalidadesPagas - i);
                    mensalidade.setMesanoref(String.valueOf(dataPagamento.getYear()));
                    mensalidade.setDatavencimento(dataPagamento);
                    mensalidade.setDataprocesamento(dataPagamento);
                    mensalidade.setStatuspagamento("LIQUIDADA");
                    mensalidade.setAssociado_id(associado);
                    mensalidade.setVlrmensalidade(vlrParcela);
                    mensalidade.setOplock(oplock);

                    mensalidadeService.save(mensalidade);

                }

                LocalDate dataNaoPagamento = dataReferencia.minusMonths(1).withDayOfMonth(3);

                Mensalidade mensalidade = new Mensalidade();
                mensalidade.setMensalidade(qtdMensalidadesPagas + 1);
                mensalidade.setMesanoref(String.valueOf(dataNaoPagamento.getYear()));
                mensalidade.setDatavencimento(dataNaoPagamento);
                mensalidade.setDataprocesamento(dataNaoPagamento);
                mensalidade.setStatuspagamento("EM ABERTO");
                mensalidade.setAssociado_id(associado);
                mensalidade.setVlrmensalidade(vlrParcela);
                mensalidade.setOplock(oplock);

                mensalidadeService.save(mensalidade);




            }
                    } else if (campos[7] == "-"){




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
