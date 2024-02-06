package br.edu.iff.lojaMateriais.repository;

import br.edu.iff.lojaMateriais.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoCompraRepository extends JpaRepository<Produto, Long> {
	
	/*
	@Query("SELECT e FROM Encomenda e WHERE e.id = :id")
	Produto buscarPeloId(@Param("id") Long id);
	 */
	
	List<Produto> findByNome(String nome);
}