package br.edu.iff.lojaMateriais.service;

import br.edu.iff.lojaMateriais.model.Carrinho;
import br.edu.iff.lojaMateriais.model.Produto;
import br.edu.iff.lojaMateriais.repository.CarrinhoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

	private final CarrinhoRepository carrinhoRepository;
	private final ProdutoService produtoService;

	public CarrinhoService(CarrinhoRepository carrinhoRepository, ProdutoService produtoService) {
		this.carrinhoRepository = carrinhoRepository;
		this.produtoService = produtoService;
	}

	public List<Carrinho> listarCarrinhos() {
		return carrinhoRepository.findAll();
	}

	public Optional<Carrinho> obterCarrinho(Long id) {

		return carrinhoRepository.findById(id);
	}

	public String adicionarProdutoNoCarrinho(Long idCarrinho, Long idProduto) {

		Carrinho carrinho = obterCarrinho(idCarrinho).get();
		Produto produto = produtoService.obterProdutoPorId(idProduto).get();

		if (carrinho == null) {
			return "Carrinho não encontrado";
		}

		if (produto == null) {
			return "Produto não encontrado";
		}

		carrinho.adicionarProduto(produto);
		carrinhoRepository.save(carrinho);

		return "Operação realizada";
	}

	public String removerProdutoDoCarrinho(Long idCarrinho, Long idProduto) {

		Optional<Carrinho> carrinhoOptional = obterCarrinho(idCarrinho);
		Optional<Produto> produtoOptional = produtoService.obterProdutoPorId(idProduto);

		if (carrinhoOptional.isEmpty()) {
			return "Carrinho não encontrado";
		}

		if (produtoOptional.isEmpty()) {
			return "Produto não encontrado";
		}

		Carrinho carrinho = carrinhoOptional.get();
		Produto produto = produtoOptional.get();

		carrinho.removerProduto(produto);
		carrinhoRepository.save(carrinho);

		return "Operação realizada";
	}

	public String deletarCarrinho(Long id) {

		if (carrinhoRepository.findById(id).isEmpty() == true) {
			return "Carrinho não encontrado";
		} else {
			carrinhoRepository.deleteById(id);

			return "Carrinho deletado com sucesso!";
		}
	}

	public void atualizarCarrinho(Carrinho carrinho) {

		carrinhoRepository.save(carrinho);
	}

	public Carrinho criarCarrinho() {

		Carrinho carrinho = new Carrinho();
		carrinhoRepository.save(carrinho);

		return carrinho;
	}

}