package br.com.solutis.desafio.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Fabricio on 22/10/2017.
 */
public class PlanoDeMidiaSites extends Report {


    public  void buildSqlWithId(Long id) {
    sql = " ";
    sql += " select \n";
    sql += " ag.nomefantasia as agencia, \n";
    sql += "         '' as midia, \n";
    sql += " cli.nomefantasia as cliente, \n";
    sql += " '' as responsavel, \n";
    sql += "         '' as campanha, \n";
    sql += " '' as formato, \n";
    sql += " '' as periodo, \n";
    sql += " '' as programa, \n";
    sql += " '' as hora, \n";
    sql += " '' as mercado, \n";
    sql += " cli.nomefantasia as veiculo, \n";
    sql += "         ag.site as site, \n";
    sql += " '' as responsavel, \n";
    sql += " pii.qtd as qtd, \n";
    sql += "         pii.valor as custo_unitario, \n";
    sql += " '' as nome \n";

    sql += " from pedido_insercao_item pii \n";
    sql += " left join pedido_insercao pi on (pi.id = pii.pedidoinsercao_id) \n";
    sql += " left join agencia ag on (ag.id = pi.agencia_id) \n";
    //sql += " left join tipomidia tm on (tm.id = ag.tipomidia_id) \n";
    sql += " left join cliente cli on (cli.id = pi.cliente_id) \n";

    sql += " where pii.pedidoinsercao_id = "+id+" \n";
    }

    public HashMap<String,Object> getParametros(){
        HashMap<String,Object> parametros = new HashMap<String,Object>();



        return  parametros;
    }
}
