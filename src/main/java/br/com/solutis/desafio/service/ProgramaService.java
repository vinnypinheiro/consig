package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Programa;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.ProgramaRepository;
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

@Service("ProgramaService")
public class ProgramaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ProgramaRepository programaRepository;

    public Programa save(Programa bean){
        return programaRepository.save(bean);
    }

    public Programa getById(Long id){
        return programaRepository.getOne(id);
    }

    public void delete(Long id){
        programaRepository.deleteById(id);
    }

    public Page<Programa> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return programaRepository.findAll(pageRequest);
    }


    public List<Programa> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Programa.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Programa> lista = crit.list();

        return lista;
    }

}
