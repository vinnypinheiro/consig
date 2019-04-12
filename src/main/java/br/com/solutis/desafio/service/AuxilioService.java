package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Associado;
import br.com.solutis.desafio.domain.Auxilio;
import br.com.solutis.desafio.domain.Mensalidade;
import br.com.solutis.desafio.domain.Parcela;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.AuxilioRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by fabricio on 02/09/18.
 */

@Service("AuxilioService")
public class AuxilioService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    AuxilioRepository auxilioRepository;

    public Auxilio save(Auxilio bean){
        return auxilioRepository.save(bean);
    }

    public Auxilio getById(Long id){
        return auxilioRepository.getOne(id);
    }

    public Auxilio getByIdCustom(Long id){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Auxilio> criteria = builder.createQuery(Auxilio.class);
        Root<Auxilio> root = criteria.from(Auxilio.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Auxilio> query = em.createQuery(criteria);
        Auxilio auxilio = query.getSingleResult();

        StringBuffer hql = new StringBuffer();

        hql.append("from Parcela p where p.auxilio_id = " + id);
        Query query3 =  em.createQuery(hql.toString());
        List<Parcela> parcelas = query3.getResultList();


        auxilio.setParcelaList(parcelas);

        if (auxilio == null){
            return null;
        }


        return auxilio;
    }

    public void delete(Long id){
        auxilioRepository.deleteById(id);
    }

    public Page<Auxilio> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return auxilioRepository.findAll(pageRequest);
    }


    public List<Auxilio> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Auxilio.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Auxilio> lista = crit.list();

        return lista;
    }

}
