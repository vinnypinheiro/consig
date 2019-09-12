package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Balanco;
import br.com.solutis.desafio.helper.filter.BalancoFilter;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.BalancoRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by fabricio on 02/09/18.
 */

@Service("BalancoService")
public class BalancoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    BalancoRepository balancoRepository;

    public Balanco save(Balanco bean){
        return balancoRepository.save(bean);
    }

    public Balanco getById(Long id){

        return balancoRepository.getOne(id);

    }

    public void delete(Long id){
        balancoRepository.deleteById(id);
    }

    public Page<Balanco> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return balancoRepository.findAll(pageRequest);
    }

    public List<Balanco> getBalanco(BalancoFilter filter) {

        String associacao = filter.getAssociacaoId();
        String convenio = filter.getConvenioid();
        LocalDate dataInicio = filter.getDataInicio();
        LocalDate dataFim = filter.getDataFim();

        String dataInicial = dataInicio.toString();
        String dataFinal = dataFim.toString();

        Session session = em.unwrap(Session.class);

       /* if (filter.getAssociacaoId() > 0 && filter.getConvenioid() <= 0 && filter.getDataFim() == null){
            List<Balanco> balancoViews =  session.createSQLQuery("SELECT * from getbyassociacao(:associacao);")
                    .addEntity(Balanco.class)
                    .setParameter("associacao",associacao)
                    .list();

            return balancoViews;

        }*/



        if (filter.getAssociacaoId() != null  && filter.getDataFim() != null ){

            List<Balanco> balancoViews =  session.createNativeQuery("select\n" +
                    "ass.nome as associado,\n" +
                    "ass.cpf,\n" +
                    "ac.nomefantasia as associacao,\n" +
                    "cv.nomefantasia as convenio,\n" +
                    "a.numeroproposta as numero_proposta,\n" +
                    "p.parcela,\n" +
                    "'periodo' as periodo,\n" +
                    "p.status as situacao_parcela,\n" +
                    "p.valor as vlr_parcela\n" +
                    "from parcela p\n" +
                    "left join auxilio a on a.id = p.auxilio_id\n" +
                    "left join associado ass on ass.id = a.associado_id\n" +
                    "left join associacao ac on ac.id = a.associacao_id\n" +
                    "left join convenio cv on cv.id = a.convenio_id\n" +
                    "where datavencimento between '"+dataInicial+"' and '"+dataFinal+"'\n" +
                    "and p.status = 'Em Aberto'\n" +
                    "and a.associacao_id in ("+ associacao+")")
                    .addEntity(Balanco.class)
                    .list();

            return balancoViews;

        }
/*
        if (filter.getAssociacaoId() > 0 && filter.getDataFim() != null ){

            List<Balanco> balancoViews =  session.createSQLQuery("SELECT * from getbyperiodo(:associacao, :datainicio, :datafim);")
                    .addEntity(Balanco.class)
                    .setParameter("associacao",associacao)
                    .setParameter("datainicio",dataInicio)
                    .setParameter("datafim",dataFim)
                    .list();

            return balancoViews;

        }*/


        List<Balanco> balancoViews =  session.createSQLQuery("select\n" +
                "ass.nome as associado,\n" +
                "ass.cpf,\n" +
                "ac.nomefantasia as associacao,\n" +
                "cv.nomefantasia as convenio,\n" +
                "a.numeroproposta as numero_proposta,\n" +
                "p.parcela,\n" +
                "p.status as situacao_parcela,\n" +
                "p.valor as vlrParcela\n" +
                "from parcela p\n" +
                "left join auxilio a on a.id = p.auxilio_id\n" +
                "left join associado ass on ass.id = a.associado_id\n" +
                "left join associacao ac on ac.id = a.associacao_id\n" +
                "left join convenio cv on cv.id = a.convenio_id\n" +
                "where datavencimento between '2019-07-01' and '2019-07-31'\n" +
                "and p.status = 'Em Aberto'\n" +
                "and a.associacao_id = 1")
                .addEntity(Balanco.class)
                .list();

        return balancoViews;

    }

    public List<Balanco> getBalancoView(Long id) {

        Session session = em.unwrap(Session.class);

            List<Balanco> balancoViews =  session.createSQLQuery("SELECT * from balanco(:ass1,:ass2);")
                    .addEntity(Balanco.class)
                    .setParameter("ass1",1)
                    .setParameter("ass2",2)
                    .list();

            return balancoViews;



    }


    public List<Balanco> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Balanco.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Balanco> lista = crit.list();

        return lista;
    }

}
