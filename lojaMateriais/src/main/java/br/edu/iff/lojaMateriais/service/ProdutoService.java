package br.edu.iff.lojaMateriais.service;

import br.edu.iff.lojaMateriais.model.Produto;
import br.edu.iff.lojaMateriais.repository.ProdutoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> obterProdutoPorId(Long id) {
		return produtoRepository.findById(id);
	}

	public String salvarProduto(String nome, String desc, float preco, int quantidade) {

		Produto produto2 = new Produto(nome, desc, preco, quantidade);
		produtoRepository.save(produto2);

		return "Operação realizada.";
	}

	public String deletarProduto(Long id) {

		if (produtoRepository.findById(id).isEmpty() == true) {
			return "Produto não encontrado.";
		} else {
			produtoRepository.deleteById(id);

			return "Produto deletado com sucesso!";
		}
	}

	public String atualizarProduto(Long id, String nome, String desc, Float preco, Integer quantidade) {

		Produto produto = produtoRepository.findById(id).orElse(null);

		if (produto == null) {
			return "Produto não encontrado.";
		} else {

			if (nome != null) {
				produto.setNome(nome);
			}

			if (desc != null) {
				produto.setDescricao(desc);
			}

			if (preco != null) {
				produto.setPreco(preco);
			}

			if (quantidade != null) {
				produto.setQuantidadeEstoque(quantidade);
			}

			produtoRepository.save(produto); // Salva as alterações no banco de dados.

			return "Atualizado com sucesso.";
		}
	}
}