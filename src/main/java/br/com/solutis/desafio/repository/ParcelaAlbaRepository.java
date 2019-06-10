package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.ParcelaAlba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaAlbaRepository extends JpaRepository<ParcelaAlba, Long>{



}
