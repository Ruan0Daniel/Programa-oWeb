package br.edu.iff.lojaMateriais.repository;

import br.edu.iff.lojaMateriais.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {


}