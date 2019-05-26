package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.EspelhoRetorno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspelhoRetornoRepository extends JpaRepository<EspelhoRetorno, Long>{



}
