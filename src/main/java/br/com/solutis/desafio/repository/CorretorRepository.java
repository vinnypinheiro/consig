package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Corretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorretorRepository extends JpaRepository<Corretor, Long>{



}
