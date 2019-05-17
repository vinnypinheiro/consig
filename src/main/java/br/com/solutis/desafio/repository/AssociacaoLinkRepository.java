package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.AssociacaoLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociacaoLinkRepository extends JpaRepository<AssociacaoLink, Long>{



}
