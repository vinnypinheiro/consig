package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.TipoMidiaDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMidiaDigitalRepository extends JpaRepository<TipoMidiaDigital, Long>{



}
