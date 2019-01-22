package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long>{



}
