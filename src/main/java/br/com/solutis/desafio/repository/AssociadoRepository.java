package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long>{



}
