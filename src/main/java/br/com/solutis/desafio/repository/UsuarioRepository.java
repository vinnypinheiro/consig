package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query(" from Usuario u where u.email = ?1")
    public Usuario findByEmail(String filterValue);

    @Query(" from Usuario u where u.email = ?1 or u.nome = ?1")
    public Usuario find(String text);


}
