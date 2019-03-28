package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Importdata;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.ImportdataRepository;
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

@Service("ImportdataService")
public class ImpostdataService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ImportdataRepository importdataRepository;

    public Importdata save(Importdata bean){
        return importdataRepository.save(bean);
    }

    public Importdata getById(Long id){
        return importdataRepository.getOne(id);
    }

    public void delete(Long id){
        importdataRepository.deleteById(id);
    }

    public Page<Importdata> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return importdataRepository.findAll(pageRequest);
    }


    public List<Importdata> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Importdata.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Importdata> lista = crit.list();

        return lista;
    }

}
