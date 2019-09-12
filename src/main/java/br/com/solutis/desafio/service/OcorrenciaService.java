package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Ocorrencia;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.OcorrenciaRepository;
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

@Service("OcorrenciaService")
public class OcorrenciaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    public Ocorrencia save(Ocorrencia bean){
        return ocorrenciaRepository.save(bean);
    }

    public List<Ocorrencia> getById(Long id){

        Session session = em.unwrap(Session.class);

        List<Ocorrencia> ocorrencias =  session.createNativeQuery("select\n" +
                "* \n" +
                "from ocorrencia \n" +
                "where associado_id = ("+ id+")")
                .addEntity(Ocorrencia.class)
                .list();

        return ocorrencias;
    }

    public void delete(Long id){
        ocorrenciaRepository.deleteById(id);
    }

    public Page<Ocorrencia> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return ocorrenciaRepository.findAll(pageRequest);
    }


    public List<Ocorrencia> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Ocorrencia.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Ocorrencia> lista = crit.list();

        return lista;
    }

}
