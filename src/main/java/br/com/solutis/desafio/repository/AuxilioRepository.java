package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Auxilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuxilioRepository extends JpaRepository<Auxilio, Long>{



}
