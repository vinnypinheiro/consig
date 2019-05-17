package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.AssociacaoLink;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.AssociacaoLinkRepository;
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

@Service("AssociacaoLinkService")
public class AssociacaoLinkService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    AssociacaoLinkRepository associacaoLinkRepository;

    public AssociacaoLink save(AssociacaoLink bean){
        return associacaoLinkRepository.save(bean);
    }

    public AssociacaoLink getById(Long id){
        return associacaoLinkRepository.getOne(id);
    }

    public void delete(Long id){
        associacaoLinkRepository.deleteById(id);
    }

    public Page<AssociacaoLink> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return associacaoLinkRepository.findAll(pageRequest);
    }


    public List<AssociacaoLink> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(AssociacaoLink.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<AssociacaoLink> lista = crit.list();

        return lista;
    }

}
