package br.com.solutis.desafio.service;
import br.com.solutis.desafio.domain.Material;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.MaterialRepository;
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

@Service("MaterialService")
public class MaterialService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    MaterialRepository materialRepository;

    public Material save(Material bean){
        return materialRepository.save(bean);
    }

    public Material getById(Long id){
        return materialRepository.getOne(id);
    }

    public void delete(Long id){
        materialRepository.deleteById(id);
    }

    public Page<Material> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return materialRepository.findAll(pageRequest);
    }


    public List<Material> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Material.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Material> lista = crit.list();

        return lista;
    }

}
