package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long>{



}
