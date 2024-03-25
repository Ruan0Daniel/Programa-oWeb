package br.edu.iff.lojaMateriais.service;

import br.edu.iff.lojaMateriais.model.Carteira;
import br.edu.iff.lojaMateriais.repository.CarteiraRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarteiraService {

	private final CarteiraRepository carteiraRepository;

	public CarteiraService(CarteiraRepository carteiraRepository) {
		this.carteiraRepository = carteiraRepository;
	}

	public List<Carteira> listarCarteiras() {
		return carteiraRepository.findAll();
	}

	public Optional<Carteira> obterCarteira(Long id) {

		return carteiraRepository.findById(id);
	}

	public String deletarCarteira(Long id) {

		if (carteiraRepository.findById(id).isEmpty() == true) {
			return "Carteira não encontrado";
		} else {
			carteiraRepository.deleteById(id);

			return "Carteira deletado com sucesso!";
		}
	}

	public String AtualizarSaldo(Long id, Float valor) {
		
		Carteira carteira = carteiraRepository.findById(id).orElse(null);

		if (carteira == null) {
			return "Produto não encontrado.";
		} else {

			if (valor != null) {
				carteira.atualizarSaldo(valor);
			}

			carteiraRepository.save(carteira); // Salva as alterações no banco de dados.

			return "Valor atualizado com sucesso.";
		}
	}

	public void atualizarCarteira(Carteira carteira) {

		carteiraRepository.save(carteira);
	}

	public Carteira criarCarteira() {

		Carteira carteira = new Carteira();
		carteiraRepository.save(carteira);

		return carteira;
	}

}