package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.RelatorioAlba;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.RelatorioAlbaRepository;
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

@Service("RelatorioAlbaService")
public class RelatorioAlbaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    RelatorioAlbaRepository relatorioAlbaRepository;

    public RelatorioAlba save(RelatorioAlba bean){
        return relatorioAlbaRepository.save(bean);
    }

    public RelatorioAlba getById(Long id){
        return relatorioAlbaRepository.getOne(id);
    }

    public void delete(Long id){
        relatorioAlbaRepository.deleteById(id);
    }

    public Page<RelatorioAlba> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return relatorioAlbaRepository.findAll(pageRequest);
    }


    public List<RelatorioAlba> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(RelatorioAlba.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<RelatorioAlba> lista = crit.list();

        return lista;
    }

}
