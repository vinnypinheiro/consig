package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Associacao;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.AssociacaoRepository;
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

@Service("AssociacaoService")
public class AssociacaoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    AssociacaoRepository associacaoRepository;

    public Associacao save(Associacao bean){
        return associacaoRepository.save(bean);
    }

    public Associacao getById(Long id){
        return associacaoRepository.getOne(id);
    }

    public void delete(Long id){
        associacaoRepository.deleteById(id);
    }

    public Page<Associacao> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return associacaoRepository.findAll(pageRequest);
    }


    public List<Associacao> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Associacao.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Associacao> lista = crit.list();

        return lista;
    }

}
