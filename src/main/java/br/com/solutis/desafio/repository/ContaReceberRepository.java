package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.ContaReceber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaReceberRepository extends JpaRepository<ContaReceber, Long>{



}
