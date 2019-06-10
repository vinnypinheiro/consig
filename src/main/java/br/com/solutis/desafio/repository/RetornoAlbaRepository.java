package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.RetornoAlba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetornoAlbaRepository extends JpaRepository<RetornoAlba, Long>{



}
