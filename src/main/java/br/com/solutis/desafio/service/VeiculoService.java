package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Veiculo;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.VeiculoRepository;
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

@Service("VeiculoService")
public class VeiculoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    VeiculoRepository veiculoRepository;

    public Veiculo save(Veiculo bean){
        return veiculoRepository.save(bean);
    }

    public Veiculo getById(Long id){
        return veiculoRepository.getOne(id);
    }

    public void delete(Long id){
        veiculoRepository.deleteById(id);
    }

    public Page<Veiculo> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return veiculoRepository.findAll(pageRequest);
    }


    public List<Veiculo> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Veiculo.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Veiculo> lista = crit.list();

        return lista;
    }

}
