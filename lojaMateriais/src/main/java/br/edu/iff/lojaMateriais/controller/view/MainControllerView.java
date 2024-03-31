package br.edu.iff.lojaMateriais.controller.view;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.lojaMateriais.model.Cliente;

@Controller
@RequestMapping(path = "")
public class MainControllerView {
	
	@GetMapping("/")
	public String index(){
		return "index";
	}
	
	@GetMapping("/gerencia")
	public String layoutBase(){
		return "gerenciarDashboard";
	}
	
	@GetMapping("/cadastro")
	public String showClientForm(Model model) {
	    model.addAttribute("cliente_add", new Cliente());
	    return "cadastro";
	}
	
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@GetMapping("/carrinho")
	public String carrinho(){
		return "carrinho";
	}
	
	@GetMapping("/produtos")
	public String produtos(){
		return "produtos";
	}
	
	@GetMapping("/faqs")
	public String faqs(){
		return "faqs";
	}
	
	@GetMapping("/sobre")
	public String sobre(){
		return "sobre";
	}
	
}
