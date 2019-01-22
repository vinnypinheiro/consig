package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Contato;
import br.com.solutis.desafio.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{



}
