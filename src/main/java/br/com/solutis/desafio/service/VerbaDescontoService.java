package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.VerbaDesconto;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.VerbaDescontoRepository;
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

@Service("VerbaDescontoService")
public class VerbaDescontoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    VerbaDescontoRepository verbadescontoRepository;

    public VerbaDesconto save(VerbaDesconto bean){
        return verbadescontoRepository.save(bean);
    }

    public VerbaDesconto getById(Long id){
        return verbadescontoRepository.getOne(id);
    }

    public void delete(Long id){
        verbadescontoRepository.deleteById(id);
    }

    public Page<VerbaDesconto> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return verbadescontoRepository.findAll(pageRequest);
    }


    public List<VerbaDesconto> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(VerbaDesconto.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<VerbaDesconto> lista = crit.list();

        return lista;
    }

}
