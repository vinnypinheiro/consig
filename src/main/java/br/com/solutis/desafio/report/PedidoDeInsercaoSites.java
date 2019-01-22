package br.com.solutis.desafio.report;


import java.util.HashMap;

/**
 * Created by Fabricio on 22/10/2017.
 */
public class PedidoDeInsercaoSites extends Report {

    @Override
    public  void buildSqlWithId(Long id) {
    sql = "";
        sql += " select \n ";
        sql += " '' as midia, \n ";
        sql += " ag.site as site, \n ";
        sql += " ag.nomefantasia as veiculo, \n ";
        sql += " ag.nomefantasia as agencia, \n ";
        sql += " ag.razaosocial as razao_social, \n ";
        sql += " ag.cidade as cidade, \n ";
        sql += " ag.telefone as tel, \n ";
        sql += " ag.contato as contato, \n ";
        sql += " aguf.nome as uf, \n ";
        sql += " '' as cep, \n ";
        sql += " ag.fax as fax, \n ";
        sql += " ''as representante, \n ";
        sql += " ag.endereco as endereco, \n ";
        sql += " ag.cnpj as cnpj, \n ";
        sql += " ag.email as email, \n ";
        sql += " ag.inscricaoestadual as incricao_estadual, \n ";
        sql += " ag.inscricaomunicipal as inscricao_municipal, \n ";
        sql += " '' as praca, \n ";
        sql += " '' as responsavel, \n ";
        sql += " '' as campanha, \n ";
        sql += " '' as formato, \n ";
        sql += " '' as periodo, \n ";
        sql += " '' as programa, \n ";
        sql += " '' as hora, \n ";
        sql += " '' as mercado, \n ";
        sql += " cli.nomefantasia as veiculo2, \n ";
        sql += " cli.nomefantasia as cliente, \n ";
        sql += " cli.razaosocial as razao_social2, \n ";
        sql += " cli.cidade as cidade2, \n ";
        sql += " cli.telefone as tel2, \n ";
        sql += " cli.contato as contato2, \n ";
        sql += " ciuf.nome as uf2, \n ";
        sql += " '' as cep2, \n ";
        sql += " cli.fax as fax2, \n ";
        sql += " ''as representante2, \n ";
        sql += " cli.endereco as endereco2, \n ";
        sql += " cli.cnpj as cnpj2, \n ";
        sql += " cli.email as email2, \n ";
        sql += " cli.inscricaoestadual as inscricao_estadual2, \n ";
        sql += " cli.inscricaomunicipal as inscricao_municipal2, \n ";
        sql += " '' as praca2, \n ";
        sql += " '' as responsavel, \n ";
        sql += " pii.qtd as qtd, \n ";
        sql += " pii.valor as custo_unitario, \n ";
        sql += " pii.dia01  as d1, \n ";
        sql += " pii.dia02  as d2, \n ";
        sql += " pii.dia03  as d3, \n ";
        sql += " pii.dia04  as d4, \n ";
        sql += " pii.dia05  as d5, \n ";
        sql += " pii.dia06  as d6, \n ";
        sql += " pii.dia07  as d7, \n ";
        sql += " pii.dia08  as d8, \n ";
        sql += " pii.dia09  as d9, \n ";
        sql += " pii.dia10  as d10, \n ";
        sql += " pii.dia11  as d11, \n ";
        sql += " pii.dia12  as d12, \n ";
        sql += " pii.dia13  as d13, \n ";
        sql += " pii.dia14  as d14, \n ";
        sql += " pii.dia15  as d15, \n ";
        sql += " pii.dia16  as d16, \n ";
        sql += " pii.dia17  as d17, \n ";
        sql += " pii.dia18  as d18, \n ";
        sql += " pii.dia19  as d19, \n ";
        sql += " pii.dia20  as d20, \n ";
        sql += " pii.dia21  as d21, \n ";
        sql += " pii.dia22  as d22, \n ";
        sql += " pii.dia23  as d23, \n ";
        sql += " pii.dia24  as d24, \n ";
        sql += " pii.dia25  as d25, \n ";
        sql += " pii.dia26  as d26, \n ";
        sql += " pii.dia27  as d27, \n ";
        sql += " pii.dia28  as d28, \n ";
        sql += " pii.dia29  as d29, \n ";
        sql += " pii.dia30  as d30, \n ";
        sql += " pii.dia31  as d31, \n ";
        sql += " to_char(pii.data, 'MM') as mes, \n ";
        sql += " to_char(pii.data, 'YYYY') as ano, \n ";
        sql += " '' as nome \n ";
        sql += " from pedidoinsercao pi \n ";
        sql += " left join pedidoinsercaoitem pii on (pii.pedidoinsercao_id = pi.id)  \n ";
        sql += " left join agencia ag on (ag.id = pi.agencia_id) \n ";
        //sql += " left join tipomidia tm on (tm.id = ag.tipomidia_id) \n ";
        sql += " left join cliente cli on (cli.id = pi.cliente_id) \n ";
        sql += " left join uf aguf on (aguf.id = ag.uf_id) \n ";
        sql += " left join uf ciuf on (ciuf.id = cli.uf_id) \n ";
        sql += " where pi.id = "+id;
        sql += "";
    }

    public HashMap<String,Object> getParametros(){
        HashMap<String,Object> parametros = new HashMap<String,Object>();

        String LOGO = "X:\\IdeiaProjects\\novo\\midiafacil\\midiafacil\\resources\\logo.png";
        parametros.put("LOGO", LOGO);


        return  parametros;
    }
}
