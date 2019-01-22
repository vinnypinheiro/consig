package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long>{



}
