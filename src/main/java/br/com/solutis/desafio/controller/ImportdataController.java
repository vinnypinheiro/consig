package br.com.solutis.desafio.controller;
import br.com.solutis.desafio.domain.*;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.service.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


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
    CorretorService corretorService;

    @Autowired
    ParcelaService parcelaService;

    @Autowired
    MensalidadeService mensalidadeService;

    long time = 0;
    int line = 2;
    int inseridos = 0;
    int minline = 0;
    int maxline = 99999999;

    //1 -FAMCRED 2-IESBA
    int associacaoID = 2 ;

    // 183596- TELCRED
    int correspondenteID  =183596 ;

    // 204327- AIRES ESCR-224566  nelia-204328
    int corretorID = 204328;

    //1- SAEB   2- SUPREV
    int convenioID =2;

    // 1- 5041 (PARCELA)   2- 5022 (MENSALIDADE)
    int verbadescontoID = 1;
    

    String oplock = "NELIA-SUPREV-IESBA";


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Importdata bean) {
        return this.buildResponse(importdataService.save(bean));
    }

    @RequestMapping(value = "/importcsv", method = RequestMethod.POST)
    public ResponseEntity importcsv() throws FileNotFoundException {


        BufferedReader br = null;
        FileReader fl =  new FileReader("X:\\DOCUMENTOS\\projetos\\DAIANE\\IMPORTACAO\\IESBA\\IESBANELIA.CSV");

        try {
            //le o csv
            br = new BufferedReader(fl);

            String linha;
            linha = br.readLine();


            //pra cada linha
            while ((linha = br.readLine()) != null && line <= maxline ) {
                if(line >= minline){
                    // divide a lina em campos informando o separador
                    String[] campos = linha.split(";");

                    //faz alguma coisa com os campos
                    if(!campos[3].trim().isEmpty()){
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


        Associacao associacao = associacaoService.getById( Long.valueOf(associacaoID));
        Corretor corretor = corretorService.getById(Long.valueOf(corretorID));
        Correspondente correspondente = correspondenteService.getById(Long.valueOf(correspondenteID));
        Convenio convenio = convenioService.getById(Long.valueOf(convenioID));
        VerbaDesconto verbaDesconto = verbaDescontoService.getById(Long.valueOf(verbadescontoID));

        System.out.println("######################## Linha--> "+line+" - Inseridos --> "+(line - (inseridos +1)));

        String cpfString = campos[3].replaceAll("[,.-]", "").trim();

           Associado associado =  associadoService.getByCpf(Long.valueOf(cpfString));

           if(associado == null ){
                associado = new Associado();

               if(campos[3] != null && !campos[3].isEmpty()){
                   associado.setNome(campos[4]);
                   associado.setCpf( Long.valueOf(cpfString) );
                   associadoService.save(associado);
               }
           }


                Double vlrmensalidade =79.0;
                associado.setVlrmensalidade(vlrmensalidade);



        String dataReserva = campos[6];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTimeReserva = LocalDate.parse(dataReserva, formatter);

        String arquivo = campos[0];


         //Valores
        if (campos[7].contains("CONTRATO")){

            Auxilio auxilio = new Auxilio();
            Double vlrParcela = Double.valueOf(campos[2].replace(".","").replace(",","."));
            Integer qtdparcelas =  Integer.parseInt(campos[5]);

            //Relacionamentos
            auxilio.setAssociacao_id(associacao);
            auxilio.setCorrespondente_id(correspondente);
            auxilio.setCorretor_id(corretor);
            auxilio.setConvenio_id(convenio);
            auxilio.setVerbadesconto_id(verbaDesconto);

            auxilio.setTipo("1º CONTRATO");

            Double vlrauxilio = Double.valueOf(campos[1].replace(".","").replace(",","."));
            auxilio.setVlrauxilio(vlrauxilio);
            auxilio.setVlrtotal(vlrParcela*qtdparcelas);
            auxilio.setPorcentagem(4);
            auxilio.setNumeroproposta(associado.getCpf().toString() + dateTimeReserva.getYear());


            auxilio.setAssociado_id(associado);
            auxilio.setDatareserva(dateTimeReserva);
            auxilio.setDataContrato(dateTimeReserva);

            auxilio.setVlrparcelas(vlrParcela);
            auxilio.setQtdparcelas(qtdparcelas);
            auxilio.setQtdparcelasnaopagas(qtdparcelas);
            auxilio.setQtdparcelaspagas(0);
            auxilio.setTotalaberto(vlrauxilio);


            auxilio.setArquivo(arquivo);
            auxilio.setOplock(oplock);


            auxilioService.save(auxilio);


            for (int i=0; i < qtdparcelas ; i++ ){

                Parcela parcela = new Parcela();
                parcela.setParcela(i+1);
                parcela.setData(dateTimeReserva.plusMonths(i+1));
                parcela.setStatus("Em Aberto");
                parcela.setAuxilio_id(auxilio);
                parcela.setOplock(oplock);

                parcela.setDatavencimento(dateTimeReserva.plusMonths(i+1).withDayOfMonth(3));

                parcela.setValor(auxilio.getVlrparcelas());

                parcelaService.save(parcela);

            }


        } else {

            Auxilio auxilio = new Auxilio();
            Double vlrParcela = Double.valueOf(campos[2].replace(".","").replace(",","."));
            Integer qtdparcelas =  Integer.parseInt(campos[5]);
            Double vlrauxilio = Double.valueOf(campos[1].replace(".","").replace(",","."));

            //Relacionamentos
            auxilio.setAssociacao_id(associacao);
            auxilio.setCorrespondente_id(correspondente);
            auxilio.setCorretor_id(corretor);
            auxilio.setConvenio_id(convenio);
            auxilio.setVerbadesconto_id(verbaDesconto);

            auxilio.setTipo("REFINANCIAMENTO");

            List<Auxilio> auxilioList = associado.getAuxilioList();

            for(Auxilio aux : auxilioList){

                    List<Parcela> parcelaList = new ArrayList<>(aux.getParcelaList());

                    Comparator<Parcela> byParcela = (e1, e2) -> Integer.compare(e2.getParcela(), e1.getParcela());
                    parcelaList.sort(byParcela.reversed());

                    for(Parcela parcela : parcelaList){

                            parcela.setStatus("LIQUIDADA REFI");
                            parcela.setValorpago(parcela.getValor());
                            parcela.setDatapagamento(parcela.getDatavencimento());
                            parcela.setOplock(oplock);

                            parcelaService.save(parcela);


                    }

                    aux.setQtdparcelasnaopagas(0);
                    aux.setQtdparcelaspagas(aux.getQtdparcelas());
                    aux.setStatus("QUITADO");
                    aux.setImportacao("S");
                    aux.setTotalaberto(0.0);
                    aux.setTotalpago(aux.getVlrauxilio());

                auxilioService.save(aux);




            }

            Double totalaberto =  vlrParcela * ((1- Math.pow((1 + 0.04),- qtdparcelas)) / 0.04 );
            Double vlrauxilioliquido = Double.valueOf(campos[1].replace(".","").replace(",","."));


            auxilio.setArquivo(arquivo);
            auxilio.setOplock(oplock);

            auxilio.setAssociado_id(associado);
            auxilio.setDatareserva(dateTimeReserva);
            auxilio.setDataContrato(dateTimeReserva);


            auxilio.setVlrparcelas(vlrParcela);
            auxilio.setTotalaberto(vlrauxilio);


            auxilio.setVlrliquidoliberado(vlrauxilioliquido);
            auxilio.setImportacao("S");
            auxilio.setVlrauxilio(vlrauxilioliquido);
            auxilio.setVlrtotal(vlrParcela*qtdparcelas);
            auxilio.setPorcentagem(5);
            auxilio.setNumeroproposta(associado.getCpf().toString() + dateTimeReserva.getYear() + ".1");

            auxilio.setQtdparcelasnaopagas(qtdparcelas );
            auxilio.setQtdparcelaspagas(0);
            auxilio.setQtdparcelas(qtdparcelas);
            auxilio.setStatus("DEFERIDO");
            auxilio.setImportacao("S");
            auxilio.setTotalaberto(totalaberto);
            auxilio.setTotalpago(0.0);

            auxilioService.save(auxilio);

            for (int i=0; i < qtdparcelas ; i++ ){

                Parcela parcela = new Parcela();
                parcela.setParcela(i+1);
                parcela.setData(dateTimeReserva.plusMonths(i+1));
                parcela.setStatus("Em Aberto");
                parcela.setAuxilio_id(auxilio);
                parcela.setOplock(oplock);

                parcela.setDatavencimento(dateTimeReserva.plusMonths(i+1).withDayOfMonth(3));

                parcela.setValor(auxilio.getVlrparcelas());

                parcelaService.save(parcela);

            }




        }






//        //cria as mensalidades
//        for (int i=0; i < qtdparcelas ; i++ ){
//
//            Mensalidade m = new Mensalidade();
//            m.setMensalidade(i+1);
//            if (m.getMensalidade() == 1){
//                m.setStatuspagamento("Enviado Folha " + dateTimeReserva.plusMonths(1));
//            }else{
//                m.setStatuspagamento("Em Aberto");
//            }
//
//            m.setAssociado_id(associado);
//            m.setDatavencimento(dateTimeReserva.plusMonths(i+1).withDayOfMonth(3));
//            m.setVlrmensalidade(associado.getVlrmensalidade());
//            m.setOplock(oplock);
//
//            mensalidadeService.save(m);
//        }


    }

    public void makeRefinanciamento(Associado associadoRefis, String[] campos  ) throws ParseException {

        Associacao associacao = associacaoService.getById( Long.valueOf(associacaoID));
        Correspondente correspondente = correspondenteService.getById(Long.valueOf(239));
        Convenio convenio = convenioService.getById(Long.valueOf(convenioID));
        VerbaDesconto verbaDesconto = verbaDescontoService.getById(Long.valueOf(2));


        //Datas
        String dataReserva = campos[9];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTimeReserva = LocalDate.parse(dataReserva, formatter);

        LocalDate dataContrato = LocalDate.now();

        //Valores
        Double vlrauxilio = Double.valueOf(campos[0].replace(".","").replace(",","."));
        Double vlrParcela = Double.valueOf(campos[2].replace(".","").replace(",","."));
        Double vlrtotal =Double.valueOf(campos[4].replace(".","").replace(",","."));

        Integer qtdparcelas =  Integer.parseInt(campos[8]);

        Auxilio auxilio = new Auxilio();
        auxilio.setAssociado_id(associadoRefis);
        auxilio.setDatareserva(dateTimeReserva);
        auxilio.setDataContrato(dateTimeReserva);
        auxilio.setOplock(oplock);


        if (vlrauxilio == null ){
            auxilio.setVlrauxilio(0.0);
        } else {
            auxilio.setVlrauxilio(vlrauxilio);
        }

        if (vlrParcela == null ){
            auxilio.setVlrparcelas(0.0);
        } else {
            auxilio.setVlrparcelas(vlrParcela);
        }

        auxilio.setQtdparcelas(qtdparcelas);
        auxilio.setVlrtotal(vlrtotal);
        auxilio.setPorcentagem(5);
        auxilio.setTipo("REFINANCIAMENTO");

        //Relacionamentos
        auxilio.setAssociacao_id(associacao);
        auxilio.setCorrespondente_id(correspondente);
        auxilio.setConvenio_id(convenio);
        auxilio.setVerbadesconto_id(verbaDesconto);

        List<Auxilio> AuxilioList = associadoRefis.getAuxilioList();

        Auxilio auxilioAnteriorGet =  AuxilioList.get(AuxilioList.size() - 1) ;
        Auxilio auxilioAnterior = auxilioService.getByIdCustom(auxilioAnteriorGet.getId()) ;

        if(associadoRefis.getAuxilioList().size() == 1){
            auxilio.setNumeroproposta(auxilioAnterior.getNumeroproposta() +".1");
        }

        if(associadoRefis.getAuxilioList().size() == 2){
            auxilio.setNumeroproposta(auxilioAnterior.getNumeroproposta()  +".2");
        }
        if(associadoRefis.getAuxilioList().size() == 3){
            auxilio.setNumeroproposta(auxilioAnterior.getNumeroproposta() +".3");
        }
        if(associadoRefis.getAuxilioList().size() == 5){
            auxilio.setNumeroproposta(auxilioAnterior.getNumeroproposta() +".4");
        }

        //datas
        LocalDate dateTimePrimeiroAuxilio =auxilioAnterior.getDatareserva();

        Period diferencaMes = Period.between(dateTimePrimeiroAuxilio.withDayOfMonth(1), dateTimeReserva.withDayOfMonth(1));
        System.out.println(diferencaMes);

        List<Parcela> parcelaListAuxilioAnterio = new ArrayList<>(auxilioAnterior.getParcelaList());

        //Atualiza parcela anterior
        for(Parcela parcela : parcelaListAuxilioAnterio){

            if (parcela.getParcela() > Integer.valueOf((int) diferencaMes.toTotalMonths()) ) {
                parcela.setStatus("PAGO REFIS");
                parcela.setDatapagamento(dateTimeReserva.plusMonths(1).withDayOfMonth(4));
                parcela.setValorpago(parcela.getValor());
                parcela.setOplock(oplock);
            }else {
                parcela.setStatus("PAGO");
                parcela.setValorpago(parcela.getValor());
                parcela.setDatapagamento(parcela.getDatavencimento());
                parcela.setOplock(oplock);
            }

            parcelaService.save(parcela);

        }

        List<Mensalidade> mensalidadeList = associadoRefis.getMensalidadeList();

        Comparator<Mensalidade> bymensalidade = (e1, e2) -> Integer.compare(e2.getMensalidade(), e1.getMensalidade());
        mensalidadeList.sort(bymensalidade.reversed());

        int diferencameses = Integer.valueOf((int) diferencaMes.toTotalMonths());

        //Atualiza Mensalidades
        for(Mensalidade mensalidade : mensalidadeList){

            if (mensalidade.getMensalidade() <= diferencameses  ) {
                mensalidade.setStatuspagamento("PAGO");
                mensalidade.setDatavencimento(mensalidade.getDatavencimento());
                mensalidade.setDataprocesamento(dateTimeReserva.plusMonths(1).withDayOfMonth(4));
                mensalidade.setVlrdescontado(mensalidade.getVlrmensalidade());
                mensalidade.setOplock(oplock);
                mensalidadeService.save(mensalidade);
            }else{
                mensalidadeService.delete(mensalidade.getId());
            }


        }

        // salva auxilio
        auxilioService.save(auxilio);

        // cria as PArcelas do novo refis
        for (int i=0; i < qtdparcelas ; i++ ){

            Parcela parcela = new Parcela();
            parcela.setParcela(i+1);
            parcela.setData(dateTimeReserva.plusMonths(i+1));
            if (parcela.getParcela() == 1){
                parcela.setStatus("Enviado Folha " + dateTimeReserva.plusMonths(1));
            }
            parcela.setStatus("Em Aberto");
            parcela.setAuxilio_id(auxilio);
            parcela.setOplock(oplock);
            parcela.setDatavencimento(dateTimeReserva.plusMonths(i+1).withDayOfMonth(3));

            parcela.setDatavencimento(dateTimeReserva.plusMonths(i+1).withDayOfMonth(3));

            parcela.setValor(auxilio.getVlrparcelas());

            parcelaService.save(parcela);

        }


        //cria as mensalidades do novo refis
        for (int i=0; i < qtdparcelas ; i++ ){

            Mensalidade m = new Mensalidade();
            m.setMensalidade(diferencameses + i+1);
            if (m.getMensalidade() == diferencameses +1){
                m.setStatuspagamento("Enviado Folha " + dateTimeReserva.plusMonths(1));
            }else{
                m.setStatuspagamento("Em Aberto");
            }

            m.setAssociado_id(associadoRefis);
            m.setVlrmensalidade(associadoRefis.getVlrmensalidade());
            m.setDatavencimento(dateTimeReserva.plusMonths(i+1).withDayOfMonth(3));
            m.setOplock(oplock);

            mensalidadeService.save(m);
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
