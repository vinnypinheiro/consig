package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Site;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.SiteRepository;
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

@Service("SiteService")
public class SiteService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    SiteRepository siteRepository;

    public Site save(Site bean){
        return siteRepository.save(bean);
    }

    public Site getById(Long id){
        return siteRepository.getOne(id);
    }

    public void delete(Long id){
        siteRepository.deleteById(id);
    }

    public Page<Site> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return siteRepository.findAll(pageRequest);
    }


    public List<Site> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Site.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Site> lista = crit.list();

        return lista;
    }

}
