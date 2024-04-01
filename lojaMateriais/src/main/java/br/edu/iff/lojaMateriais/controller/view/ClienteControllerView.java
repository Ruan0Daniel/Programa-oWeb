package br.edu.iff.lojaMateriais.controller.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.lojaMateriais.model.Cliente;
import br.edu.iff.lojaMateriais.service.CarteiraService;
import br.edu.iff.lojaMateriais.service.ClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "cliente")
public class ClienteControllerView {

	@Autowired
	public ClienteService clienteService;

	@Autowired
	public CarteiraService carteiraService;

	// GET e POST para cadastro de cliente

	@GetMapping("/cadastro")
	public String exibirFormularioCliente(Model model) throws Exception{

		model.addAttribute("cliente", new Cliente());
		return "cadastro";
	}

	@PostMapping("/cadastro")
	public String adicionarCliente(@Valid @ModelAttribute Cliente cliente, BindingResult result) throws Exception{

		if (result.hasErrors()) {
			return "cadastro";

		} else {
			clienteService.adicionarCliente(cliente.getUsuario().getEmail(), cliente.getUsuario().getSenha(),
					cliente.getNome(), cliente.getCpf(), cliente.getTelefone());
			return "redirect:/cliente/listar";
		}
	}

	// GET e POST para cadastro de cliente a partir da gerência

	@GetMapping("/cadastrarGerencia")
	public String cadastrarClienteGerencia(Model model) throws Exception{

		model.addAttribute("cliente", new Cliente());
		return "admin/cliente/adicionarClientes";
	}

	@PostMapping("/cadastrarGerencia")
	public String cadastrarClienteGerencia(@Valid @ModelAttribute Cliente cliente, BindingResult resultado)  throws Exception{

		if (resultado.hasErrors()) {
			return "admin/cliente/adicionarClientes";
		} else {
			clienteService.adicionarCliente(cliente.getUsuario().getEmail(), cliente.getUsuario().getSenha(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone());
			return "redirect:/cliente/listar";
		}
	}

	// GET e POST para atualizar cliente
	
	@GetMapping("/atualizar/{id}")
	public String editarCliente(@PathVariable("id") Long id, Model model) throws Exception {

		Cliente cliente = clienteService.buscarCliente(id).orElse(null);
		model.addAttribute("cliente", cliente);
		
		return "admin/cliente/editarClientes";
	}
	
	
	@PostMapping("/atualizar/{id}")
	public String atualizarCliente(@PathVariable("id") Long id, @Valid @ModelAttribute Cliente cliente,
			BindingResult resultado) throws Exception{

		String senha = cliente.getUsuario().getSenha();

		Cliente cliente2 = clienteService.buscarCliente(id).orElse(null);

		if (senha.isEmpty()) {

			senha = cliente2.getUsuario().getSenha();
		}

		if (resultado.hasErrors()) {
			
			return "admin/cliente/editarClientes";
		} else {
			clienteService.atualizarCliente(id, cliente.getUsuario().getEmail(), senha,
					cliente.getNome(), cliente.getCpf(), cliente.getTelefone());

			carteiraService.editarSaldo(cliente2.getCarteira().getId(), cliente.getCarteira().getSaldoDisponivel());
		
			return "redirect:/cliente/listar";
			
		}
	}
	
	
	@GetMapping("/listar")
	public String listarClientes(Model model) throws Exception {

		model.addAttribute("cliente_lista", clienteService.listarClientes());

		return "admin/cliente/gerenciarClientes";
	}


	@GetMapping("/deletarGerencia/{id}")
	public String deletarCliente(@PathVariable("id") Long id) throws Exception {
		clienteService.deletarCliente(id);
		return "redirect:/cliente/listar";
	}
}
