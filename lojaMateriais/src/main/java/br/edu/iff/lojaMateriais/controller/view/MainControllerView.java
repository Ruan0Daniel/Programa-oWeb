package br.edu.iff.lojaMateriais.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class MainControllerView {
	
	@GetMapping("/")
	public String index() throws Exception{
		return "index";
	}
	
	@GetMapping("/gerencia")
	public String layoutBase() throws Exception{
		return "gerenciarDashboard";
	}

	@GetMapping("/login")
	public String login() throws Exception{
		return "login";
	}
	
	@GetMapping("/carrinho")
	public String carrinho() throws Exception{
		return "carrinho";
	}
	
	@GetMapping("/produtos")
	public String produtos() throws Exception{
		return "produtos";
	}
	
	@GetMapping("/faqs")
	public String faqs() throws Exception{
		return "faqs";
	}
	
	@GetMapping("/sobre")
	public String sobre() throws Exception{
		return "sobre";
	}
	
}
