package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Peca;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.PecaRepository;
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

@Service("PecaService")
public class PecaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    PecaRepository pecaRepository;

    public Peca save(Peca bean){
        return pecaRepository.save(bean);
    }

    public Peca getById(Long id){
        return pecaRepository.getOne(id);
    }

    public void delete(Long id){
        pecaRepository.deleteById(id);
    }

    public Page<Peca> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return pecaRepository.findAll(pageRequest);
    }


    public List<Peca> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Peca.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Peca> lista = crit.list();

        return lista;
    }

}
