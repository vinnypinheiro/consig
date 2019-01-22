package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Formato;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.FormatoRepository;
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

@Service("FormatoService")
public class FormatoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    FormatoRepository formatoRepository;

    public Formato save(Formato bean){
        return formatoRepository.save(bean);
    }

    public Formato getById(Long id){
        return formatoRepository.getOne(id);
    }

    public void delete(Long id){
        formatoRepository.deleteById(id);
    }

    public Page<Formato> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return formatoRepository.findAll(pageRequest);
    }


    public List<Formato> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Formato.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Formato> lista = crit.list();

        return lista;
    }

}
