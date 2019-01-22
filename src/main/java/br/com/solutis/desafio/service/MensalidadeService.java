package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Mensalidade;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.MensalidadeRepository;
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

@Service("MensalidadeService")
public class MensalidadeService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    MensalidadeRepository mensalidadeRepository;

    public Mensalidade save(Mensalidade bean){
        return mensalidadeRepository.save(bean);
    }

    public Mensalidade getById(Long id){
        return mensalidadeRepository.getOne(id);
    }

    public void delete(Long id){
        mensalidadeRepository.deleteById(id);
    }

    public Page<Mensalidade> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return mensalidadeRepository.findAll(pageRequest);
    }


    public List<Mensalidade> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Mensalidade.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Mensalidade> lista = crit.list();

        return lista;
    }

}
