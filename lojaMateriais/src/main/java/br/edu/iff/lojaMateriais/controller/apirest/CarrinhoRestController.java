package br.edu.iff.lojaMateriais.controller.apirest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.lojaMateriais.model.Carrinho;
import br.edu.iff.lojaMateriais.service.CarrinhoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/carrinhos")
public class CarrinhoRestController {

	private final CarrinhoService carrinhoService;

	public CarrinhoRestController(CarrinhoService carrinhoService) {
		this.carrinhoService = carrinhoService;
	}

	@PutMapping("/adicionar/{idCarrinho}/{idProduto}")
	@ResponseBody
	@Operation(summary = "Adicionar produtos no carrinho.")
	public String adicionarProdutoNoCarrinho(@PathVariable("idCarrinho") Long idCarrinho,
			@PathVariable("idProduto") Long idProduto) {

		return carrinhoService.adicionarProdutoNoCarrinho(idCarrinho, idProduto);
	}

	@PutMapping("/remover/{idCarrinho}/{idProduto}")
	@ResponseBody
	@Operation(summary = "remover produtos no carrinho.")
	public String removerProdutoDoCarrinho(@PathVariable("idCarrinho") Long idCarrinho,
			@PathVariable("idProduto") Long idProduto) {

		return carrinhoService.removerProdutoDoCarrinho(idCarrinho, idProduto);
	}

	@GetMapping("")
	@ResponseBody
	@Operation(summary = "listar todos os carrinhos.")
	public List<Carrinho> listarCarrinhos() {
		return carrinhoService.listarCarrinhos();
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Procurar carrinho.")
	public Optional<Carrinho> buscarCarrinho(Long idCarrinho) {
		return carrinhoService.obterCarrinho(idCarrinho);
	}

	/*-------------------------------------------------------------------------------------------------------*/

	@PostMapping()
	@ResponseBody
	@Operation(summary = "Criar um carrinho.")
	public String criarCarrinho() {

		if (carrinhoService.criarCarrinho() == null) {
			return "Carrinho não foi criado.";
		} else {
			return "Carrinho criado com sucesso.";
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Excluir carrinho.")
	public String excluirCarrinho(Long id) {
		return carrinhoService.deletarCarrinho(id);
	}

}
