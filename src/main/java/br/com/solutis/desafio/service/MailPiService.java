package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.MailPi;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.MailPiRepository;
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

@Service("MailPiService")
public class MailPiService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    MailPiRepository mailpiRepository;

    public MailPi save(MailPi bean){
        return mailpiRepository.save(bean);
    }

    public MailPi getById(Long id){
        return mailpiRepository.getOne(id);
    }

    public void delete(Long id){
        mailpiRepository.deleteById(id);
    }

    public Page<MailPi> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return mailpiRepository.findAll(pageRequest);
    }


    public List<MailPi> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(MailPi.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<MailPi> lista = crit.list();

        return lista;
    }

}
