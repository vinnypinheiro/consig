package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.PlanoMidiaInsersaoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoMidiaInsersaoItemRepository extends JpaRepository<PlanoMidiaInsersaoItem, Long>{



}
