package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.PlanoMidia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoMidiaRepository extends JpaRepository<PlanoMidia, Long>{



}
