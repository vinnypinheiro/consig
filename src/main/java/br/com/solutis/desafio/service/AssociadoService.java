package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Associado;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.AssociadoRepository;
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

@Service("AssociadoService")
public class AssociadoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    AssociadoRepository associadoRepository;

    public Associado save(Associado bean){
        return associadoRepository.save(bean);
    }

    public Associado getById(Long id){
        return associadoRepository.getOne(id);
    }

    public void delete(Long id){
        associadoRepository.deleteById(id);
    }

    public Page<Associado> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return associadoRepository.findAll(pageRequest);
    }


    public List<Associado> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Associado.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Associado> lista = crit.list();

        return lista;
    }

}
