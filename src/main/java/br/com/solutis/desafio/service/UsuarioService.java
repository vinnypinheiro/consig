package br.com.solutis.desafio.service;

import br.com.solutis.desafio.domain.Usuario;
import br.com.solutis.desafio.helper.filter.FilterData;
import br.com.solutis.desafio.helper.filter.WhereClause;
import br.com.solutis.desafio.repository.UsuarioRepository;
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

@Service("UsuarioService")
public class UsuarioService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario save(Usuario bean){
        return usuarioRepository.save(bean);
    }

    public Usuario getById(Long id){
        return usuarioRepository.getOne(id);
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    public Page<Usuario> getList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 20, Sort.Direction.ASC, "id");
        return usuarioRepository.findAll(pageRequest);
    }

    public Usuario simpleSearch(String text){
        return usuarioRepository.find(text);
    }

    public List<Usuario> select(final FilterData filter) {
        Criteria crit = em.unwrap(Session.class).createCriteria(Usuario.class);
        for(WhereClause wc :filter.getWhereClauses()) {
            //crit.add(Restrictions.ilike(wc.getName(), wc.getIniValue()));
        }
        List<Usuario> lista = crit.list();

        return lista;
    }

}
