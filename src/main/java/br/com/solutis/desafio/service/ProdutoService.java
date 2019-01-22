package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Produto;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.ProdutoRepository;
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

@Service("ProdutoService")
public class ProdutoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto save(Produto bean){
        return produtoRepository.save(bean);
    }

    public Produto getById(Long id){
        return produtoRepository.getOne(id);
    }

    public void delete(Long id){
        produtoRepository.deleteById(id);
    }

    public Page<Produto> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return produtoRepository.findAll(pageRequest);
    }


    public List<Produto> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Produto.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Produto> lista = crit.list();

        return lista;
    }

}
