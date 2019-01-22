package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Convenio;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.ConvenioRepository;
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

@Service("ConvenioService")
public class ConvenioService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ConvenioRepository convenioRepository;

    public Convenio save(Convenio bean){
        return convenioRepository.save(bean);
    }

    public Convenio getById(Long id){
        return convenioRepository.getOne(id);
    }

    public void delete(Long id){
        convenioRepository.deleteById(id);
    }

    public Page<Convenio> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return convenioRepository.findAll(pageRequest);
    }


    public List<Convenio> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Convenio.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Convenio> lista = crit.list();

        return lista;
    }

}
