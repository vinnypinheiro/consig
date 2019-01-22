package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.TipoProduto;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.TipoProdutoRepository;
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

@Service("TipoProdutoService")
public class TipoProdutoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    TipoProdutoRepository tipoprodutoRepository;

    public TipoProduto save(TipoProduto bean){
        return tipoprodutoRepository.save(bean);
    }

    public TipoProduto getById(Long id){
        return tipoprodutoRepository.getOne(id);
    }

    public void delete(Long id){
        tipoprodutoRepository.deleteById(id);
    }

    public Page<TipoProduto> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return tipoprodutoRepository.findAll(pageRequest);
    }


    public List<TipoProduto> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(TipoProduto.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<TipoProduto> lista = crit.list();

        return lista;
    }

}
