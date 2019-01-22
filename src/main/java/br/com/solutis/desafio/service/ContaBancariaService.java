package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.ContaBancaria;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.ContaBancariaRepository;
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

@Service("ContaBancariaService")
public class ContaBancariaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ContaBancariaRepository contaBancariaRepository;

    public ContaBancaria save(ContaBancaria bean){
        return contaBancariaRepository.save(bean);
    }

    public ContaBancaria getById(Long id){
        return contaBancariaRepository.getOne(id);
    }

    public void delete(Long id){
        contaBancariaRepository.deleteById(id);
    }

    public Page<ContaBancaria> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return contaBancariaRepository.findAll(pageRequest);
    }


    public List<ContaBancaria> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(ContaBancaria.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<ContaBancaria> lista = crit.list();

        return lista;
    }

}
