package br.edu.iff.lojaMateriais.repository;

import br.edu.iff.lojaMateriais.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	

}