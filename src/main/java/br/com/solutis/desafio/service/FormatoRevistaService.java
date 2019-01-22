package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.FormatoRevista;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.FormatoRevistaRepository;
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

@Service("FormatoRevistaService")
public class FormatoRevistaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    FormatoRevistaRepository formatorevistaRepository;

    public FormatoRevista save(FormatoRevista bean){
        return formatorevistaRepository.save(bean);
    }

    public FormatoRevista getById(Long id){
        return formatorevistaRepository.getOne(id);
    }

    public void delete(Long id){
        formatorevistaRepository.deleteById(id);
    }

    public Page<FormatoRevista> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return formatorevistaRepository.findAll(pageRequest);
    }


    public List<FormatoRevista> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(FormatoRevista.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<FormatoRevista> lista = crit.list();

        return lista;
    }

}
