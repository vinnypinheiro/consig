package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.RetornoAlba;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.RetornoAlbaRepository;
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

@Service("RetornoAlbaService")
public class RetornoAlbaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    RetornoAlbaRepository retornoAlbaRepository;

    public RetornoAlba save(RetornoAlba bean){
        return retornoAlbaRepository.save(bean);
    }

    public RetornoAlba getById(Long id){
        return retornoAlbaRepository.getOne(id);
    }

    public void delete(Long id){
        retornoAlbaRepository.deleteById(id);
    }

    public Page<RetornoAlba> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return retornoAlbaRepository.findAll(pageRequest);
    }


    public List<RetornoAlba> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(RetornoAlba.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<RetornoAlba> lista = crit.list();

        return lista;
    }

}
