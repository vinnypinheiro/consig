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

        int associacao = filter.getAssociacaoId();
        int convenio = filter.getConvenioid();
        LocalDate dataInicio = filter.getDataInicio();
        LocalDate dataFim = filter.getDataFim();

        Session session = em.unwrap(Session.class);

        if (filter.getAssociacaoId() > 0 && filter.getConvenioid() <= 0 ){
            List<Balanco> balancoViews =  session.createSQLQuery("SELECT * from getbyassociacao(:associacao);")
                    .addEntity(Balanco.class)
                    .setParameter("associacao",associacao)
                    .list();

            return balancoViews;

        }

        if (filter.getAssociacaoId() <= 0 && filter.getConvenioid() > 0 ){
            List<Balanco> balancoViews =  session.createSQLQuery("SELECT * from getbyassociacao(:convenio);")
                    .addEntity(Balanco.class)
                    .setParameter("convenio",convenio)
                    .list();

            return balancoViews;

        }

        List<Balanco> balancoViews =  session.createSQLQuery("SELECT * from getbyassociacao(:associacao);")
                .addEntity(Balanco.class)
                .setParameter("associacao",associacao)
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
