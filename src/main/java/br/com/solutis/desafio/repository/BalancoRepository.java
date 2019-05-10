package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Balanco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalancoRepository extends JpaRepository<Balanco, Long>{



}
