package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Balanco;
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

    public List<Balanco> getBalancoView(Long id) {

        Long idBalanco = id;

        Session session = em.unwrap(Session.class);

        if (idBalanco == Long.valueOf(1)) {

            List<Balanco> balancoViews =  session.createSQLQuery("SELECT * from BalancoView").getResultList();

            return balancoViews;

        } else {

            List<Balanco> balancoViews =  session.createSQLQuery("SELECT * from BalancoView2").getResultList();

            return balancoViews;

        }


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
