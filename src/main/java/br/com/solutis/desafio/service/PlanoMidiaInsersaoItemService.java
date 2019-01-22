package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.PlanoMidiaInsersaoItem;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.PlanoMidiaInsersaoItemRepository;
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

@Service("PlanoMidiaInsersaoItemService")
public class PlanoMidiaInsersaoItemService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    PlanoMidiaInsersaoItemRepository planomidiainsersaoitemRepository;

    public PlanoMidiaInsersaoItem save(PlanoMidiaInsersaoItem bean){
        return planomidiainsersaoitemRepository.save(bean);
    }

    public PlanoMidiaInsersaoItem getById(Long id){
        return planomidiainsersaoitemRepository.getOne(id);
    }

    public void delete(Long id){
        planomidiainsersaoitemRepository.deleteById(id);
    }

    public Page<PlanoMidiaInsersaoItem> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return planomidiainsersaoitemRepository.findAll(pageRequest);
    }


    public List<PlanoMidiaInsersaoItem> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(PlanoMidiaInsersaoItem.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<PlanoMidiaInsersaoItem> lista = crit.list();

        return lista;
    }

}
