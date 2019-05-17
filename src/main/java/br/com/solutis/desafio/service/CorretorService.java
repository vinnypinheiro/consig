package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Corretor;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.CorretorRepository;
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

@Service("CorretorService")
public class CorretorService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    CorretorRepository corretorRepository;

    public Corretor save(Corretor bean){
        return corretorRepository.save(bean);
    }

    public Corretor getById(Long id){
        return corretorRepository.getOne(id);
    }

    public void delete(Long id){
        corretorRepository.deleteById(id);
    }

    public Page<Corretor> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return corretorRepository.findAll(pageRequest);
    }


    public List<Corretor> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Corretor.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Corretor> lista = crit.list();

        return lista;
    }

}
