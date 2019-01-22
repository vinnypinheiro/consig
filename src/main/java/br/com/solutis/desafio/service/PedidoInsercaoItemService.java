package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.PedidoInsercaoItem;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.PedidoInsercaoItemRepository;
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

@Service("PedidoInsercaoItemService")
public class PedidoInsercaoItemService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    PedidoInsercaoItemRepository pedidoinsercaoitemRepository;

    public PedidoInsercaoItem save(PedidoInsercaoItem bean){
        return pedidoinsercaoitemRepository.save(bean);
    }

    public PedidoInsercaoItem getById(Long id){
        return pedidoinsercaoitemRepository.getOne(id);
    }

    public void delete(Long id){
        pedidoinsercaoitemRepository.deleteById(id);
    }

    public Page<PedidoInsercaoItem> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return pedidoinsercaoitemRepository.findAll(pageRequest);
    }


    public List<PedidoInsercaoItem> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(PedidoInsercaoItem.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<PedidoInsercaoItem> lista = crit.list();

        return lista;
    }

}
