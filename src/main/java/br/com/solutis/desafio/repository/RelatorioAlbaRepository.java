package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.RelatorioAlba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioAlbaRepository extends JpaRepository<RelatorioAlba, Long>{



}
