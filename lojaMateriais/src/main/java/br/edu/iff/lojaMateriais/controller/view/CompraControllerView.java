package br.edu.iff.lojaMateriais.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.lojaMateriais.model.Cliente;
import br.edu.iff.lojaMateriais.model.Compra;
import br.edu.iff.lojaMateriais.model.Item;
import br.edu.iff.lojaMateriais.service.CarrinhoService;
import br.edu.iff.lojaMateriais.service.CarteiraService;
import br.edu.iff.lojaMateriais.service.ClienteService;
import br.edu.iff.lojaMateriais.service.CompraService;
import br.edu.iff.lojaMateriais.service.ProdutoService;
import br.edu.iff.lojaMateriais.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "compra")
public class CompraControllerView {

	@Autowired
	public CompraService compraService;


	@GetMapping("/finalizarCompra/{idCliente}")
	public String finalizarCompra(@PathVariable("idCliente") Long idCliente, HttpSession session, HttpServletRequest request) throws Exception {

		compraService.finalizarCompra(idCliente);

		return "gerenciarDashboard";
	}
	
	@GetMapping("/listar")
	public String listarCompras(Model model) throws Exception {
		
		model.addAttribute("listaDeCompras", compraService.listarCompras());

		return "gerenciarCompras";
	}
	
	
	@GetMapping("/listarItensDaCompra/{id}")
	public String listarItensDaCompra(@PathVariable("id") Long idCompra, Model model) throws Exception {
		
		model.addAttribute("listaDeItens", compraService.obterCompra(idCompra).get().getListaDeProdutos());

		return "gerenciarCompras";
	}
	
	@GetMapping("/deletarCompra/{id}")
	public String deletarCompra(@PathVariable("id") Long idCompra, HttpSession session, HttpServletRequest request) throws Exception {

		compraService.deletarCompra(idCompra);
		
		 // Obtenha a URL da página anterior
	    String referer = request.getHeader("Referer");
	    
	    // Redirecione de volta para a página anterior
	    return "redirect:" + referer;
	}
	
	@GetMapping("/editar/{id}")
	public String editarCompra(@PathVariable("id") Long idCompra, Model model) throws Exception {
		
		model.addAttribute("editarCompra", compraService.obterCompra(idCompra).get());

		return "gerenciarCompras";
	}
	
	@PostMapping("/atualizar/{id}")
	public String atualizarCompra(@PathVariable("id") Long idCompra, @ModelAttribute Compra compra, HttpSession session, HttpServletRequest request) throws Exception {
		
		compraService.atualizarCompra(idCompra, compra.getDataHora(), compra.getPrecoFinal());
		
		 // Obtenha a URL da página anterior
	    String referer = request.getHeader("Referer");
	    
	    // Redirecione de volta para a página anterior
	    return "redirect:" + referer;
	}
	
	
}
