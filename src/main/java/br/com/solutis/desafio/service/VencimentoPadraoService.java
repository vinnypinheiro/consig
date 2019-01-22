package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.VencimentoPadrao;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.VencimentoPadraoRepository;
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

@Service("VencimentoPadraoService")
public class VencimentoPadraoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    VencimentoPadraoRepository vencimentopadraoRepository;

    public VencimentoPadrao save(VencimentoPadrao bean){
        return vencimentopadraoRepository.save(bean);
    }

    public VencimentoPadrao getById(Long id){
        return vencimentopadraoRepository.getOne(id);
    }

    public void delete(Long id){
        vencimentopadraoRepository.deleteById(id);
    }

    public Page<VencimentoPadrao> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return vencimentopadraoRepository.findAll(pageRequest);
    }


    public List<VencimentoPadrao> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(VencimentoPadrao.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<VencimentoPadrao> lista = crit.list();

        return lista;
    }

}
