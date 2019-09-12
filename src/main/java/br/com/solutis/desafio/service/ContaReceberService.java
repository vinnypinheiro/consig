package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.ContaReceber;
import br.com.solutis.desafio.helper.filter.BalancoFilter;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.ContaReceberRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by fabricio on 02/09/18.
 */

@Service("ContaReceberService")
public class ContaReceberService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ContaReceberRepository contaReceberRepository;

    public List<ContaReceber> getContasReceber(BalancoFilter filter) {

        String associacao = filter.getAssociacaoId();
        String convenio = filter.getConvenioid();
        LocalDate dataInicio = filter.getDataInicio();
        LocalDate dataFim = filter.getDataFim();

        String dataInicial = dataInicio.toString();
        String dataFinal = dataFim.toString();

        Session session = em.unwrap(Session.class);

            List<ContaReceber> balancoViews =  session.createNativeQuery("select\n" +
                    "* \n" +
                    "from conta_receber \n" +
                    "where data_vencimento between '"+dataInicial+"' and '"+dataFinal+"'\n" +
                    "and associacao_id in ("+ associacao+")")
                    .addEntity(ContaReceber.class)
                    .list();

            return balancoViews;



    }

    public ContaReceber save(ContaReceber bean){
        return contaReceberRepository.save(bean);
    }

    public ContaReceber getById(Long id){
        return contaReceberRepository.getOne(id);
    }

    public void delete(Long id){
        contaReceberRepository.deleteById(id);
    }

    public Page<ContaReceber> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return contaReceberRepository.findAll(pageRequest);
    }


    public List<ContaReceber> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(ContaReceber.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<ContaReceber> lista = crit.list();

        return lista;
    }

}
