package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.EspelhoRetorno;
import br.com.solutis.desafio.domain.Auxilio;
import br.com.solutis.desafio.domain.Mensalidade;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.EspelhoRetornoRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by fabricio on 02/09/18.
 */

@Service("EspelhoRetornoService")
public class EspelhoRetornoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    EspelhoRetornoRepository espelhoRetornoRepository;

    public EspelhoRetorno save(EspelhoRetorno bean){
        return espelhoRetornoRepository.save(bean);
    }

    public EspelhoRetorno getById(Long id){
        return espelhoRetornoRepository.getOne(id);
    }


    public void delete(Long id){
        espelhoRetornoRepository.deleteById(id);
    }

    public Page<EspelhoRetorno> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 1000, Sort.Direction.ASC, "id");
        return espelhoRetornoRepository.findAll(pageRequest);
    }


    public List<EspelhoRetorno> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(EspelhoRetorno.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<EspelhoRetorno> lista = crit.list();

        return lista;
    }

}
