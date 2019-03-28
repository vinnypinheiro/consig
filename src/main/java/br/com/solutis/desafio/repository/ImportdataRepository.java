package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Importdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportdataRepository extends JpaRepository<Importdata, Long>{



}
