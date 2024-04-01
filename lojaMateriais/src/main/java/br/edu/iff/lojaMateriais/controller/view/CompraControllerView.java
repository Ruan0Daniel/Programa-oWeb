package br.edu.iff.lojaMateriais.controller.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.lojaMateriais.model.Compra;
import br.edu.iff.lojaMateriais.service.CompraService;

@Controller
@RequestMapping(path = "compra")
public class CompraControllerView {

	@Autowired
	public CompraService compraService;


	@GetMapping("/finalizarCompra/{idCliente}")
	public String finalizarCompra(@PathVariable("idCliente") Long idCliente) throws Exception {

		compraService.finalizarCompra(idCliente);

		return "redirect:/compra/listar";
	}
	
	@GetMapping("/listar")
	public String listarCompras(Model model) throws Exception {
		
		model.addAttribute("listaDeCompras", compraService.listarCompras());

		return "admin/compra/listarCompras";
	}
	
	
	@GetMapping("/listarItensDaCompra/{id}")
	public String listarItensDaCompra(@PathVariable("id") Long idCompra, Model model) throws Exception {
		
		model.addAttribute("listaDeItens", compraService.obterCompra(idCompra).get().getListaDeProdutos());

		return "admin/compra/itensDaCompra";
	}
	
	@GetMapping("/deletarCompra/{id}")
	public String deletarCompra(@PathVariable("id") Long idCompra) throws Exception {

		compraService.deletarCompra(idCompra);
		
	    return "redirect:/compra/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCompra(@PathVariable("id") Long idCompra, Model model) throws Exception {
		
		model.addAttribute("editarCompra", compraService.obterCompra(idCompra).get());

		return "admin/compra/editarCompras";
	}
	
	@PostMapping("/atualizar/{id}")
	public String atualizarCompra(@PathVariable("id") Long idCompra, @ModelAttribute Compra compra) throws Exception {
		
		compraService.atualizarCompra(idCompra, compra.getDataHora(), compra.getPrecoFinal());
		
	    return "redirect:/compra/listar";
	}
	
	
}
