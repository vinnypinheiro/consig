package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Correspondente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrespondenteRepository extends JpaRepository<Correspondente, Long>{



}
