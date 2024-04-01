package br.edu.iff.lojaMateriais.controller.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.lojaMateriais.model.Cliente;
import br.edu.iff.lojaMateriais.model.Item;
import br.edu.iff.lojaMateriais.service.CarrinhoService;
import br.edu.iff.lojaMateriais.service.ClienteService;
import br.edu.iff.lojaMateriais.service.ProdutoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "carrinho")
public class CarrinhoControllerView {

	@Autowired
	public ClienteService clienteService;

	@Autowired
	public CarrinhoService carrinhoService;
	
	@Autowired
	public ProdutoService produtoService;
	
		
	@GetMapping("/carrinho/{id}")
	public String carrinhoCliente(@PathVariable("id") Long id, Model model) throws Exception {
		Cliente cliente = clienteService.buscarCliente(id).orElse(null);
		model.addAttribute("item", new Item());
		model.addAttribute("cliente", cliente);
		model.addAttribute("lista_produtos", produtoService.listarProdutos());
		return "admin/cliente/gerenciarClientes";
	}


	@PostMapping("/adicionarProdutoNoCarrinho/{idCarrinho}")
	public String adicionarProdutoNoCarrinho(@PathVariable("idCarrinho") Long idCarrinho, Item item, HttpSession session, HttpServletRequest request) throws Exception{
	    
	    carrinhoService.adicionarProdutoNoCarrinho(idCarrinho, item.getProduto().getId(), item.getQuantidade());
	    
	    // Obtenha a URL da página anterior
	    String referer = request.getHeader("Referer");
	    
	    // Redirecione de volta para a página anterior
	    return "redirect:" + referer;
	}

	@GetMapping("/removerProdutoDoCarrinho/{idCarrinho}/{idItem}")
	public String removerProdutoNoCarrinhoGet(@PathVariable("idCarrinho") Long idCarrinho, @PathVariable("idItem") Long idItem, HttpSession session, HttpServletRequest request) throws Exception {
	    
		carrinhoService.removerProdutoDoCarrinho(idCarrinho, idItem);  
	    // Obtenha a URL da página anterior
	    String referer = request.getHeader("Referer");
	    // Redirecione de volta para a página anterior
	    return "redirect:" + referer;
	}
	
	@PostMapping("/editarItemNoCarrinho")
	public String editarItemNoCarrinho(@RequestParam("carrinhoId") Long carrinhoId, @RequestParam("itemId") Long itemId,
			@RequestParam("novaQuantidade") int novaQuantidade) throws Exception {

		carrinhoService.atualizarProduto(carrinhoId, itemId, novaQuantidade);
		return "redirect:/carrinho/carrinho/" + carrinhoId;
	}
	
}
