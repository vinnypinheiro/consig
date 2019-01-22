package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.PedidoInsercao;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.PedidoInsercaoRepository;
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

@Service("PedidoInsercaoService")
public class PedidoInsercaoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    PedidoInsercaoRepository pedidoinsercaoRepository;

    public PedidoInsercao save(PedidoInsercao bean){
        return pedidoinsercaoRepository.save(bean);
    }

    public PedidoInsercao getById(Long id){
        return pedidoinsercaoRepository.getOne(id);
    }

    public void delete(Long id){
        pedidoinsercaoRepository.deleteById(id);
    }

    public Page<PedidoInsercao> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return pedidoinsercaoRepository.findAll(pageRequest);
    }


    public List<PedidoInsercao> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(PedidoInsercao.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<PedidoInsercao> lista = crit.list();

        return lista;
    }

}
