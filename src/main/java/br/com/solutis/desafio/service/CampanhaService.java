package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Campanha;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.CampanhaRepository;
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

@Service("CampanhaService")
public class CampanhaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    CampanhaRepository campanhaRepository;

    public Campanha save(Campanha bean){
        return campanhaRepository.save(bean);
    }

    public Campanha getById(Long id){
        return campanhaRepository.getOne(id);
    }

    public void delete(Long id){
        campanhaRepository.deleteById(id);
    }

    public Page<Campanha> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return campanhaRepository.findAll(pageRequest);
    }


    public List<Campanha> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Campanha.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Campanha> lista = crit.list();

        return lista;
    }

}
