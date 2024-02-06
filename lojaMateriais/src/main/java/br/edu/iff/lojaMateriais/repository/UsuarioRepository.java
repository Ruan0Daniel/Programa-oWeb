package br.edu.iff.lojaMateriais.repository;

import br.edu.iff.lojaMateriais.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
	
}
