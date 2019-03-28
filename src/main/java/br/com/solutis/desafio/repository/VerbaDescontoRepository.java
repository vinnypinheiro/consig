package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.VerbaDesconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerbaDescontoRepository extends JpaRepository<VerbaDesconto, Long>{



}
