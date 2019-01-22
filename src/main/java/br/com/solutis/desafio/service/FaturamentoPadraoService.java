package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.FaturamentoPadrao;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.FaturamentoPadraoRepository;
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

@Service("FaturamentoPadraoService")
public class FaturamentoPadraoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    FaturamentoPadraoRepository faturamentopadraoRepository;

    public FaturamentoPadrao save(FaturamentoPadrao bean){
        return faturamentopadraoRepository.save(bean);
    }

    public FaturamentoPadrao getById(Long id){
        return faturamentopadraoRepository.getOne(id);
    }

    public void delete(Long id){
        faturamentopadraoRepository.deleteById(id);
    }

    public Page<FaturamentoPadrao> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return faturamentopadraoRepository.findAll(pageRequest);
    }


    public List<FaturamentoPadrao> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(FaturamentoPadrao.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<FaturamentoPadrao> lista = crit.list();

        return lista;
    }

}
