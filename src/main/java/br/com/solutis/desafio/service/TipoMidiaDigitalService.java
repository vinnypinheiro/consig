package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.TipoMidiaDigital;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.TipoMidiaDigitalRepository;
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

@Service("TipoMidiaDigitalService")
public class TipoMidiaDigitalService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    TipoMidiaDigitalRepository tipomidiadigitalRepository;

    public TipoMidiaDigital save(TipoMidiaDigital bean){
        return tipomidiadigitalRepository.save(bean);
    }

    public TipoMidiaDigital getById(Long id){
        return tipomidiadigitalRepository.getOne(id);
    }

    public void delete(Long id){
        tipomidiadigitalRepository.deleteById(id);
    }

    public Page<TipoMidiaDigital> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return tipomidiadigitalRepository.findAll(pageRequest);
    }


    public List<TipoMidiaDigital> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(TipoMidiaDigital.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<TipoMidiaDigital> lista = crit.list();

        return lista;
    }

}
