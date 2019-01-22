package br.com.solutis.desafio.repository;

import br.com.solutis.desafio.domain.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long>{



}
