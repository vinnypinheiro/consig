package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Pais;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.PaisRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.hibernate.criterion.Restrictions.ilike;

/**
 * Created by fabricio on 02/09/18.
 */

@Service("PaisService")
public class PaisService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    PaisRepository paisRepository;

    public Pais save(Pais bean){
        return paisRepository.save(bean);
    }

    public Pais getById(Long id){
        return paisRepository.getOne(id);
    }

    public void delete(Long id){
        paisRepository.deleteById(id);
    }

    public Page<Pais> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return paisRepository.findAll(pageRequest);
    }


    public List<Pais> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Pais.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Pais> lista = crit.list();

        return lista;
    }

}
