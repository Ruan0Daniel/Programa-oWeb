package br.edu.iff.lojaMateriais.repository;

import br.edu.iff.lojaMateriais.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}