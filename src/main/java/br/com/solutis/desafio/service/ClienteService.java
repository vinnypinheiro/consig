package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Cliente;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.ClienteRepository;
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

@Service("ClienteService")
public class ClienteService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente save(Cliente bean){
        return clienteRepository.save(bean);
    }

    public Cliente getById(Long id){
        return clienteRepository.getOne(id);
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

    public Page<Cliente> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return clienteRepository.findAll(pageRequest);
    }


    public List<Cliente> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Cliente.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Cliente> lista = crit.list();

        return lista;
    }

}
