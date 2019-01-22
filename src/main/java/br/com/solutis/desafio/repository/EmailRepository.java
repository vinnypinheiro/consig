package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{



}
