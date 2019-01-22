package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Uf;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.UfRepository;
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

@Service("UfService")
public class UfService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UfRepository ufRepository;

    public Uf save(Uf bean){
        return ufRepository.save(bean);
    }

    public Uf getById(Long id){
        return ufRepository.getOne(id);
    }

    public void delete(Long id){
        ufRepository.deleteById(id);
    }

    public Page<Uf> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return ufRepository.findAll(pageRequest);
    }


    public List<Uf> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Uf.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Uf> lista = crit.list();

        return lista;
    }

}
