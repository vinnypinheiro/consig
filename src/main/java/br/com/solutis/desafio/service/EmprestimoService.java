package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Emprestimo;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.EmprestimoRepository;
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

@Service("EmprestimoService")
public class EmprestimoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    EmprestimoRepository emprestimoRepository;

    public Emprestimo save(Emprestimo bean){
        return emprestimoRepository.save(bean);
    }

    public Emprestimo getById(Long id){
        return emprestimoRepository.getOne(id);
    }

    public void delete(Long id){
        emprestimoRepository.deleteById(id);
    }

    public Page<Emprestimo> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return emprestimoRepository.findAll(pageRequest);
    }


    public List<Emprestimo> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Emprestimo.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Emprestimo> lista = crit.list();

        return lista;
    }

}
