package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Telefone;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.TelefoneRepository;
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

@Service("TelefoneService")
public class TelefoneService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    TelefoneRepository telefoneRepository;

    public Telefone save(Telefone bean){
        return telefoneRepository.save(bean);
    }

    public Telefone getById(Long id){
        return telefoneRepository.getOne(id);
    }

    public void delete(Long id){
        telefoneRepository.deleteById(id);
    }

    public Page<Telefone> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return telefoneRepository.findAll(pageRequest);
    }


    public List<Telefone> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Telefone.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Telefone> lista = crit.list();

        return lista;
    }

}
