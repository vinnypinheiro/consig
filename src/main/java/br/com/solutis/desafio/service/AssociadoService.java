package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Associado;
import br.com.solutis.desafio.domain.Auxilio;
import br.com.solutis.desafio.domain.Mensalidade;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.Filtro;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.AssociadoRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by fabricio on 02/09/18.
 */

@Service("AssociadoService")
public class AssociadoService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    AssociadoRepository associadoRepository;

    public  List<Associado> getAssociadoByCrit( Filtro filtro) {

        String campo = filtro.getCampo();
        String valor = filtro.getValor();

        Session session = em.unwrap(Session.class);

        List<Associado> associado  = session.createNativeQuery(
                "select id, nome, cpf, telefone, email  from associado " +
                        " where nome ilike '%"+valor+"%'" )
                .getResultList();


        return  associado ;

    }

    public Associado save(Associado bean){
        return associadoRepository.save(bean);
    }

    public Associado getById(Long id){
        return associadoRepository.getOne(id);
    }

    public Associado getByIdCustom(Long id){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Associado> criteria = builder.createQuery(Associado.class);
        Root<Associado> root = criteria.from(Associado.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Associado> query = em.createQuery(criteria);
        Associado associado = query.getSingleResult();

        StringBuffer hql = new StringBuffer();

        hql.append("from Mensalidade m where m.associado_id = " + id);
        Query query3 =  em.createQuery(hql.toString());
        List<Mensalidade> mensalidades = query3.getResultList();

        StringBuffer hql2 = new StringBuffer();

        hql2.append("from Auxilio a where a.associado_id = " + id);
        Query query2 =  em.createQuery(hql2.toString());
        List<Auxilio> auxilios = query2.getResultList();

        associado.setAuxilioList(auxilios);
        associado.setMensalidadeList(mensalidades);


        if (associado == null){
            return null;
        }


        return associado;
    }

    public Associado getByCpf(Long cpf) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Associado> criteria = builder.createQuery(Associado.class);
        Root<Associado> root = criteria.from(Associado.class);
        Path<Long> cpf1 = root.get("cpf");
        criteria.select(root).where(builder.equal(root.get("cpf"), cpf));
        TypedQuery<Associado> query = em.createQuery(criteria);

        System.out.println("CPF : " + cpf);


        Associado associado = null;
        try {
            associado = query.getSingleResult();
            StringBuffer hql = new StringBuffer();

            hql.append("from Mensalidade m where m.associado_id = " + associado.getId());
            Query query3 = em.createQuery(hql.toString());
            List<Mensalidade> mensalidades = query3.getResultList();

            StringBuffer hql2 = new StringBuffer();

            hql2.append("from Auxilio a where a.associado_id = " + associado.getId());
            Query query2 = em.createQuery(hql2.toString());
            List<Auxilio> auxilios = query2.getResultList();

            associado.setAuxilioList(auxilios);
            associado.setMensalidadeList(mensalidades);

            if (associado == null) {
                return null;
            }


        } catch (NoResultException nre) {
            // Code for handling NoResultException
        } catch (NonUniqueResultException nure) {
            // Code for handling NonUniqueResultException
        }


        return associado;
    }

    public void delete(Long id){
        associadoRepository.deleteById(id);
    }

    public Page<Associado> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 1000, Sort.Direction.ASC, "id");
        return associadoRepository.findAll(pageRequest);
    }


    public List<Associado> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Associado.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Associado> lista = crit.list();

        return lista;
    }

}
