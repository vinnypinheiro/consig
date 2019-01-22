package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Endereco;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.EnderecoRepository;
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

@Service("EnderecoService")
public class EnderecoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Endereco save(Endereco bean){
        return enderecoRepository.save(bean);
    }

    public Endereco getById(Long id){
        return enderecoRepository.getOne(id);
    }

    public void delete(Long id){
        enderecoRepository.deleteById(id);
    }

    public Page<Endereco> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return enderecoRepository.findAll(pageRequest);
    }


    public List<Endereco> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Endereco.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Endereco> lista = crit.list();

        return lista;
    }

}
