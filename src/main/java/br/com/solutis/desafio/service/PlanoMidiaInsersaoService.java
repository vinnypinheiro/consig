package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.PlanoMidiaInsersao;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.PlanoMidiaInsersaoRepository;
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

@Service("PlanoMidiaInsersaoService")
public class PlanoMidiaInsersaoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    PlanoMidiaInsersaoRepository planomidiainsersaoRepository;

    public PlanoMidiaInsersao save(PlanoMidiaInsersao bean){
        return planomidiainsersaoRepository.save(bean);
    }

    public PlanoMidiaInsersao getById(Long id){
        return planomidiainsersaoRepository.getOne(id);
    }

    public void delete(Long id){
        planomidiainsersaoRepository.deleteById(id);
    }

    public Page<PlanoMidiaInsersao> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return planomidiainsersaoRepository.findAll(pageRequest);
    }


    public List<PlanoMidiaInsersao> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(PlanoMidiaInsersao.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<PlanoMidiaInsersao> lista = crit.list();

        return lista;
    }

}
