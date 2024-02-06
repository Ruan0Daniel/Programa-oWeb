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

import br.edu.iff.lojaMateriais.model.Compra;
import br.edu.iff.lojaMateriais.service.CompraService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/compras")
public class CompraRestController {

	private final CompraService compraService;

	public CompraRestController(CompraService compraService) {
		this.compraService = compraService;
	}

	@PostMapping("{idCliente}")
	@ResponseBody
	@Operation(summary = "Criar processo de compra.")
	public String CriarCompra(@PathVariable("idCliente") Long idCliente) {
		return compraService.criarCompra(idCliente);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Confirmar compra.")
	public String confirmarCompra(@PathVariable("id") Long id) {

		return compraService.confirmarCompra(id);

	}

	/*
	 * @PutMapping("/{id}")
	 * 
	 * @ResponseBody public String atualizarCompra() {
	 * 
	 * return null; }
	 */

	@DeleteMapping("/{idCompra}")
	@ResponseBody
	@Operation(summary = "Deletar compra.")
	public String deletarCompra(@PathVariable("idCompra") Long idCompra) {
		return compraService.deletarCompra(idCompra);
	}

	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar compras.")
	public List<Compra> listarCompras() {
		return compraService.listarCompras();
	}

	@GetMapping("/{idCompra}")
	@ResponseBody
	@Operation(summary = "Obter compra.")
	public Optional<Compra> buscarCompra(@PathVariable("idCompra") Long idCompra) {
		return compraService.obterCompra(idCompra);
	}

}
