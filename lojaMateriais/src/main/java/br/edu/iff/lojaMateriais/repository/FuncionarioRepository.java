package br.edu.iff.lojaMateriais.repository;

import br.edu.iff.lojaMateriais.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {


}