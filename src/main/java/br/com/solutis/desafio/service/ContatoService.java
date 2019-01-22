package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Contato;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.ContatoRepository;
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

@Service("ContatoService")
public class ContatoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ContatoRepository contatoRepository;

    public Contato save(Contato bean){
        return contatoRepository.save(bean);
    }

    public Contato getById(Long id){
        return contatoRepository.getOne(id);
    }

    public void delete(Long id){
        contatoRepository.deleteById(id);
    }

    public Page<Contato> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return contatoRepository.findAll(pageRequest);
    }


    public List<Contato> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Contato.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Contato> lista = crit.list();

        return lista;
    }

}
