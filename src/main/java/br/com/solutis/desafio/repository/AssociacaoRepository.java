package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Associacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociacaoRepository extends JpaRepository<Associacao, Long>{



}
