package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Parcela;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.ParcelaRepository;
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

@Service("ParcelaService")
public class ParcelaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ParcelaRepository parcelaRepository;

    public Parcela save(Parcela bean){
        return parcelaRepository.save(bean);
    }

    public Parcela getById(Long id){
        return parcelaRepository.getOne(id);
    }

    public void delete(Long id){
        parcelaRepository.deleteById(id);
    }

    public Page<Parcela> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return parcelaRepository.findAll(pageRequest);
    }


    public List<Parcela> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Parcela.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Parcela> lista = crit.list();

        return lista;
    }

}
