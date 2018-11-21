package br.com.solutis.desafio.report;

import br.com.solutis.desafio.helper.PropertiesReader;

import java.util.HashMap;

/**
 * Created by Fabricio on 22/10/2017.
 */
public class PlanoDeMidiaRadios extends Report {

    @Override
    public  void buildSqlWithId(Long id) {
        sql = " ";
        sql += " select \n";
        sql += " ag.nomefantasia as agencia, \n";
        sql += "         'midia' as midia, \n";
        sql += " cli.nomefantasia as cliente, \n";
        sql += " '' as responsavel, \n";
        sql += "         '' as campanha, \n";
        sql += " '' as formato, \n";
        sql += " '' as periodo, \n";
        sql += " '' as programa, \n";
        sql += " '' as hora, \n";
        sql += " '' as mercado, \n";
        sql += " cli.nomefantasia as veiculo, \n";
        sql += " ag.site as site, \n";
        sql += " '' as responsavel, \n";
        sql += " pii.qtd as qtd, \n";
        sql += " pii.valor as custo_unitario, \n";

        sql += " pii.dia01  as d1, \n";
        sql += " pii.dia02  as d2, \n";
        sql += " pii.dia03  as d3, \n";
        sql += " pii.dia04  as d4, \n";
        sql += " pii.dia05  as d5, \n";
        sql += " pii.dia06  as d6, \n";
        sql += " pii.dia07  as d7, \n";
        sql += " pii.dia08  as d8, \n";
        sql += " pii.dia09  as d9, \n";
        sql += " pii.dia10  as d10, \n";
        sql += " pii.dia11  as d11, \n";
        sql += " pii.dia12  as d12, \n";
        sql += " pii.dia13  as d13, \n";
        sql += " pii.dia14  as d14, \n";
        sql += " pii.dia15  as d15, \n";
        sql += " pii.dia16  as d16, \n";
        sql += " pii.dia17  as d17, \n";
        sql += " pii.dia18  as d18, \n";
        sql += " pii.dia19  as d19, \n";
        sql += " pii.dia20  as d20, \n";
        sql += " pii.dia21  as d21, \n";
        sql += " pii.dia22  as d22, \n";
        sql += " pii.dia23  as d23, \n";
        sql += " pii.dia24  as d24, \n";
        sql += " pii.dia25  as d25, \n";
        sql += " pii.dia26  as d26, \n";
        sql += " pii.dia27  as d27, \n";
        sql += " pii.dia28  as d28, \n";
        sql += " pii.dia29  as d29, \n";
        sql += " pii.dia30  as d30, \n";
        sql += " pii.dia31  as d31, \n";
        sql += " to_char(pii.data, 'MM') as mes, \n";
        sql += " to_char(pii.data, 'YYYY') as ano, \n";
        sql += " '' as nome \n";

        sql += " from pedidoinsercaoitem pii \n";
        sql += " left join pedidoinsercao pi on (pi.id = pii.pedidoinsercao_id) \n";
        sql += " left join agencia ag on (ag.id = pi.agencia_id) \n";
        //sql += " left join tipomidia tm on (tm.id = ag.tipomidia_id) \n";
        sql += " left join cliente cli on (cli.id = pi.cliente_id) \n";

        sql += " where pii.pedidoinsercao_id = "+id+" \n";
    }

    public HashMap<String,Object> getParametros(){
        HashMap<String,Object> parametros = new HashMap<String,Object>();

        String LOGO = PropertiesReader.getAplicationPath()+"resources/logo.png";
        parametros.put("LOGO", LOGO);


        return  parametros;
    }
}
