package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.ContaBancaria;
import br.com.solutis.desafio.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long>{



}
