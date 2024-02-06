package br.edu.iff.lojaMateriais.controller.apirest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.lojaMateriais.model.Produto;
import br.edu.iff.lojaMateriais.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/produtos")
public class ProdutoRestController {

	private final ProdutoService produtoService;

	public ProdutoRestController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar produto.")
	public String adicionarProduto(String nome, String desc, float preco, int quatidade) {

		return produtoService.salvarProduto(nome, desc, preco, quatidade);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar produto.")
	public String atualizarProduto(@PathVariable("id") Long id, @RequestParam(required = false) String nome,
			@RequestParam(required = false) String desc, @RequestParam(required = false) Float preco,
			@RequestParam(required = false) Integer quantidade) {

		return produtoService.atualizarProduto(id, nome, desc, preco, quantidade);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar produto.")
	public String deletarProduto(@PathVariable Long id) {

		return produtoService.deletarProduto(id);
	}

	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar produtos.")
	public List<Produto> listarProdutos() {

		return produtoService.listarProdutos();
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Buscar produtos.")
	public Optional<Produto> buscarProduto(@PathVariable("id") Long id) {

		return produtoService.obterProdutoPorId(id);
	}

}
