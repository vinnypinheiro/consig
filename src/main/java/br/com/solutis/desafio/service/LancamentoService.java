package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Lancamento;
import br.com.solutis.desafio.domain.Auxilio;
import br.com.solutis.desafio.domain.Mensalidade;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.LancamentoRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by fabricio on 02/09/18.
 */

@Service("LancamentoService")
public class LancamentoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    LancamentoRepository lancamentoRepository;

    public Lancamento save(Lancamento bean){
        return lancamentoRepository.save(bean);
    }

    public Lancamento getById(Long id){
        return lancamentoRepository.getOne(id);
    }

    public Lancamento getByIdCustom(Long id){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Lancamento> query = em.createQuery(criteria);
        Lancamento lancamento = query.getSingleResult();

        StringBuffer hql = new StringBuffer();

        hql.append("from Mensalidade m where m.lancamento_id = " + id);
        Query query3 =  em.createQuery(hql.toString());
        List<Mensalidade> mensalidades = query3.getResultList();

        StringBuffer hql2 = new StringBuffer();

        hql2.append("from Auxilio a where a.lancamento_id = " + id);
        Query query2 =  em.createQuery(hql2.toString());
        List<Auxilio> auxilios = query2.getResultList();



        if (lancamento == null){
            return null;
        }


        return lancamento;
    }

    public Lancamento getByCpf(Long cpf) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);
        Path<Long> cpf1 = root.get("cpf");
        criteria.select(root).where(builder.equal(root.get("cpf"), cpf));
        TypedQuery<Lancamento> query = em.createQuery(criteria);

        System.out.println("CPF : " + cpf);


        Lancamento lancamento = null;
        try {
            lancamento = query.getSingleResult();
            StringBuffer hql = new StringBuffer();

            hql.append("from Mensalidade m where m.lancamento_id = " + lancamento.getId());
            Query query3 = em.createQuery(hql.toString());
            List<Mensalidade> mensalidades = query3.getResultList();

            StringBuffer hql2 = new StringBuffer();

            hql2.append("from Auxilio a where a.lancamento_id = " + lancamento.getId());
            Query query2 = em.createQuery(hql2.toString());
            List<Auxilio> auxilios = query2.getResultList();



            if (lancamento == null) {
                return null;
            }


        } catch (NoResultException nre) {
            // Code for handling NoResultException
        } catch (NonUniqueResultException nure) {
            // Code for handling NonUniqueResultException
        }


        return lancamento;
    }

    public void delete(Long id){
        lancamentoRepository.deleteById(id);
    }

    public Page<Lancamento> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 1000, Sort.Direction.ASC, "id");
        return lancamentoRepository.findAll(pageRequest);
    }


    public List<Lancamento> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Lancamento.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Lancamento> lista = crit.list();

        return lista;
    }

}
