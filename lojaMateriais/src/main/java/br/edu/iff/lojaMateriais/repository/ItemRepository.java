package br.edu.iff.lojaMateriais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.lojaMateriais.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	

	
}