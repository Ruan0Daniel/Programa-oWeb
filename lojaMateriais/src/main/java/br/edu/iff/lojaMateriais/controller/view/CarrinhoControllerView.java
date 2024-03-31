package br.edu.iff.lojaMateriais.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.lojaMateriais.model.Cliente;
import br.edu.iff.lojaMateriais.model.Item;
import br.edu.iff.lojaMateriais.service.CarrinhoService;
import br.edu.iff.lojaMateriais.service.CarteiraService;
import br.edu.iff.lojaMateriais.service.ClienteService;
import br.edu.iff.lojaMateriais.service.ItemService;
import br.edu.iff.lojaMateriais.service.ProdutoService;
import br.edu.iff.lojaMateriais.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "carrinho")
public class CarrinhoControllerView {

	@Autowired
	public ClienteService clienteService;

	@Autowired
	public UsuarioService usuarioService;

	@Autowired
	public CarteiraService carteiraService;

	@Autowired
	public CarrinhoService carrinhoService;
	
	@Autowired
	public ProdutoService produtoService;
	

	@GetMapping("/carrinho/{id}")
	public String carrinhoCliente(@PathVariable("idCliente") Long idCliente, Model model) throws Exception {

		// clienteService.buscarCliente(id).orElse(null).getCarrinho().getListaDeProdutos()

		Cliente cliente = clienteService.buscarCliente(idCliente).orElse(null);
		
		
		model.addAttribute("item", new Item());
		model.addAttribute("cliente", cliente);
		model.addAttribute("lista_produtos", produtoService.listarProdutos());
		return "gerenciarClientes";
	}


	@PostMapping("/adicionarProdutoNoCarrinho/{idCarrinho}")
	public String adicionarProdutoNoCarrinho(@PathVariable("idCarrinho") Long idCarrinho, Item item, HttpSession session, HttpServletRequest request) {
	    
	    carrinhoService.adicionarProdutoNoCarrinho(idCarrinho, item.getProduto().getId(), item.getQuantidade());
	    
	    // Obtenha a URL da página anterior
	    String referer = request.getHeader("Referer");
	    
	    // Redirecione de volta para a página anterior
	    return "redirect:" + referer;
	}

	@GetMapping("/removerProdutoDoCarrinho/{idCarrinho}/{idItem}")
	public String removerProdutoNoCarrinhoGet(@PathVariable("idCarrinho") Long idCarrinho, @PathVariable("idItem") Long idItem, HttpSession session, HttpServletRequest request) {
	    
		carrinhoService.removerProdutoDoCarrinho(idCarrinho, idItem);  
	    // Obtenha a URL da página anterior
	    String referer = request.getHeader("Referer");
	    // Redirecione de volta para a página anterior
	    return "redirect:" + referer;
	}
	@GetMapping("/deletarGerencia/{id}")
	public String deletarCliente(@PathVariable("id") Long id, Model model) throws Exception {

		clienteService.deletarCliente(id);
		return "gerenciarDashboard";
	}
	
}
