package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Email;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.EmailRepository;
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

@Service("EmailService")
public class EmailService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    EmailRepository emailRepository;

    public Email save(Email bean){
        return emailRepository.save(bean);
    }

    public Email getById(Long id){
        return emailRepository.getOne(id);
    }

    public void delete(Long id){
        emailRepository.deleteById(id);
    }

    public Page<Email> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return emailRepository.findAll(pageRequest);
    }


    public List<Email> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Email.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Email> lista = crit.list();

        return lista;
    }

}
