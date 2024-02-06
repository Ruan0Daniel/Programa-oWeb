package br.edu.iff.lojaMateriais.repository;

import br.edu.iff.lojaMateriais.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {


}