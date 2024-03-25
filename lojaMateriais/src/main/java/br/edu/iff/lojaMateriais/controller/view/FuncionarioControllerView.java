package br.edu.iff.lojaMateriais.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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

import br.edu.iff.lojaMateriais.model.Funcionario;
import br.edu.iff.lojaMateriais.service.FuncionarioService;
import br.edu.iff.lojaMateriais.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "funcionario")
public class FuncionarioControllerView {

	@Autowired
	public FuncionarioService funcionarioService;

	@Autowired
	public UsuarioService usuarioService;

	@GetMapping("/adicionar")
	public String adicionarCliente(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("adicionar_funcionario", new Funcionario());

		String resposta = request.getParameter("resposta");

		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "gerenciarFuncionarios";
	}
	

	@GetMapping("/listar")
	public String listarClientes(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("listar_funcionarios", funcionarioService.listarFuncionarios());

		return "gerenciarFuncionarios";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCliente(@PathVariable("id") Long id, Model model) throws Exception {

	    Funcionario funcionario = funcionarioService.buscarFuncionario(id).orElse(null);
	    model.addAttribute("editar_funcionario", funcionario);
	    return "gerenciarFuncionarios";
	}
	
	@GetMapping("/deletarGerencia/{id}")
	public String deletarCliente(@PathVariable("id") Long id, Model model) throws Exception {
		
		funcionarioService.deletarFuncionario(id);
		return "gerenciarDashboard";
	}
	
	@PostMapping("/cadastrarGerencia")
	public String cadastrarGerencia(@ModelAttribute Funcionario funcionario, BindingResult resultado, Model model) {

		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = funcionarioService.adicionarFuncionario(	funcionario.getUsuario().getEmail(), funcionario.getUsuario().getSenha(), 
																		funcionario.getNome(), funcionario.getCpf(), funcionario.getTelefone(), 
																		funcionario.getFuncao(), funcionario.getSalario(), funcionario.getDataAdimissao());
					
			try {
				return "redirect:/gerencia?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}
	
	@PostMapping("/atualizar/{id}")
	public String atualizarCliente(@PathVariable("id") Long id, @ModelAttribute Funcionario funcionario, BindingResult resultado, Model model) {

		String senha = funcionario.getUsuario().getSenha();
		
		Funcionario funcionario2 = funcionarioService.buscarFuncionario(id).orElse(null);
		
		if (senha.isEmpty()) {

			senha = funcionario2.getUsuario().getSenha();
		}
		
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = funcionarioService.atualizarFuncionario(  id, funcionario.getUsuario().getEmail(), senha, 
																		funcionario.getNome(), funcionario.getCpf(), funcionario.getTelefone(), 
																		funcionario.getFuncao(), funcionario.getSalario(), funcionario.getDataAdimissao());
			
			try {
				return "redirect:/gerencia?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

}
