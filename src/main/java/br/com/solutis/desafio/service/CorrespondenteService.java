package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Correspondente;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.CorrespondenteRepository;
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

@Service("CorrespondenteService")
public class CorrespondenteService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    CorrespondenteRepository correspondenteRepository;

    public Correspondente save(Correspondente bean){
        return correspondenteRepository.save(bean);
    }

    public Correspondente getById(Long id){
        return correspondenteRepository.getOne(id);
    }

    public void delete(Long id){
        correspondenteRepository.deleteById(id);
    }

    public Page<Correspondente> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return correspondenteRepository.findAll(pageRequest);
    }


    public List<Correspondente> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Correspondente.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Correspondente> lista = crit.list();

        return lista;
    }

}
