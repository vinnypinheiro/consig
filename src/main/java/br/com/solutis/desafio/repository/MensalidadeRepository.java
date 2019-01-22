package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long>{



}
