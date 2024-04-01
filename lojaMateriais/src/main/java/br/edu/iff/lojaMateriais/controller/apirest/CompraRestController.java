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

import br.edu.iff.lojaMateriais.model.Compra;
import br.edu.iff.lojaMateriais.service.CompraService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/compras")
public class CompraRestController {

	private final CompraService compraService;

	public CompraRestController(CompraService compraService) throws Exception{
		this.compraService = compraService;
	}

	@PostMapping("/{idCliente}")
	@ResponseBody
	@Operation(summary = "Confirmar compra.")
	public String confirmarCompra(@PathVariable("idCliente") Long idCliente) throws Exception{

		return compraService.finalizarCompra(idCliente);

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
	public String deletarCompra(@PathVariable("idCompra") Long idCompra) throws Exception{
		return compraService.deletarCompra(idCompra);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar compra.")
	public String atualizarItem(@PathVariable("id") Long idCompra, @RequestParam(required = false) String dataHora, @RequestParam(required = false) Float valorFinal) throws Exception{

		return compraService.atualizarCompra(idCompra, dataHora, valorFinal);
	}	

	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar compras.")
	public List<Compra> listarCompras() throws Exception{
		return compraService.listarCompras();
	}

	@GetMapping("/{idCompra}")
	@ResponseBody
	@Operation(summary = "Obter compra.")
	public Optional<Compra> buscarCompra(@PathVariable("idCompra") Long idCompra) throws Exception{
		return compraService.obterCompra(idCompra);
	}

}
