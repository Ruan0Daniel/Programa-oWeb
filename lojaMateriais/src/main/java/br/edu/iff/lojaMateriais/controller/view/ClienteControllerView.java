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
import br.edu.iff.lojaMateriais.service.ProdutoService;
import br.edu.iff.lojaMateriais.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "cliente")
public class ClienteControllerView {

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
	
	
	
	@GetMapping("/adicionar")
	public String adicionarCliente(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("cliente_add", new Cliente());

		String resposta = request.getParameter("resposta");

		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "gerenciarClientes";
	}

	@GetMapping("/listar")
	public String listarClientes(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("cliente_lista", clienteService.listarClientes());

		return "gerenciarClientes";
	}

	@GetMapping("/editar/{id}")
	public String editarCliente(@PathVariable("id") Long id, Model model) throws Exception {

		Cliente cliente = clienteService.buscarCliente(id).orElse(null);
		model.addAttribute("cliente_editar", cliente);
		return "gerenciarClientes";
	}

	@GetMapping("/deletarGerencia/{id}")
	public String deletarCliente(@PathVariable("id") Long id, Model model) throws Exception {

		clienteService.deletarCliente(id);
		return "gerenciarDashboard";
	}

	@GetMapping("/carrinho/{id}")
	public String carrinhoCliente(@PathVariable("id") Long id, Model model) throws Exception {

		// clienteService.buscarCliente(id).orElse(null).getCarrinho().getListaDeProdutos()

		Cliente cliente = clienteService.buscarCliente(id).orElse(null);
		
		
		model.addAttribute("item", new Item());
		model.addAttribute("cliente", cliente);
		model.addAttribute("lista_produtos", produtoService.listarProdutos());
		return "gerenciarClientes";
	}

	@PostMapping("/cadastrarGerencia")
	public String cadastrarGerencia(@ModelAttribute Cliente cliente, BindingResult resultado, Model model) {

		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = clienteService.adicionarCliente(cliente.getUsuario().getEmail(),
					cliente.getUsuario().getSenha(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone());
			try {
				return "redirect:/gerencia?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	@PostMapping("/cadastrarCliente")
	public String cadastrarCliente(@RequestParam("email") String email, @RequestParam("senha") String senha,
			@ModelAttribute Cliente cliente, BindingResult resultado, Model model) {

		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = clienteService.adicionarCliente(email, senha, cliente.getNome(), cliente.getCpf(),
					cliente.getTelefone());
			try {
				return "redirect:/login?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	@PostMapping("/atualizar/{id}")
	public String atualizarCliente(@PathVariable("id") Long id, @ModelAttribute Cliente cliente,
			BindingResult resultado, Model model) {

		String senha = cliente.getUsuario().getSenha();

		Cliente cliente2 = clienteService.buscarCliente(id).orElse(null);

		if (senha.isEmpty()) {

			senha = cliente2.getUsuario().getSenha();
		}

		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = clienteService.atualizarCliente(id, cliente.getUsuario().getEmail(), senha,
					cliente.getNome(), cliente.getCpf(), cliente.getTelefone());

			carteiraService.editarSaldo(cliente2.getCarteira().getId(), cliente.getCarteira().getSaldoDisponivel());

			try {
				return "redirect:/gerencia?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

	@PostMapping("/adicionarProdutoNoCarrinho/{idCarrinho}")
	public String adicionarProdutoNoCarrinho(@PathVariable("idCarrinho") Long idCarrinho, Item item, HttpSession session, HttpServletRequest request) {
	    // Recupere o produto com base no ID do produto (você precisará implementar este método)
	    
	    carrinhoService.adicionarProdutoNoCarrinho(idCarrinho, item.getProduto().getId(), item.getQuantidade());
	    
	    // Obtenha a URL da página anterior
	    String referer = request.getHeader("Referer");
	    
	    // Redirecione de volta para a página anterior
	    return "redirect:" + referer;
	}
	
}
