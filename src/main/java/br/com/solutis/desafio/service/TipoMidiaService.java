package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.TipoMidia;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.TipoMidiaRepository;
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

@Service("TipoMidiaService")
public class TipoMidiaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    TipoMidiaRepository tipomidiaRepository;

    public TipoMidia save(TipoMidia bean){
        return tipomidiaRepository.save(bean);
    }

    public TipoMidia getById(Long id){
        return tipomidiaRepository.getOne(id);
    }

    public void delete(Long id){
        tipomidiaRepository.deleteById(id);
    }

    public Page<TipoMidia> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return tipomidiaRepository.findAll(pageRequest);
    }


    public List<TipoMidia> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(TipoMidia.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<TipoMidia> lista = crit.list();

        return lista;
    }

}
