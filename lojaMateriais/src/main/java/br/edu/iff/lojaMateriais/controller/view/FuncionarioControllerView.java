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

import br.edu.iff.lojaMateriais.model.Funcionario;
import br.edu.iff.lojaMateriais.service.FuncionarioService;
import br.edu.iff.lojaMateriais.service.UsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "funcionario")
public class FuncionarioControllerView {

	@Autowired
	public FuncionarioService funcionarioService;

	@Autowired
	public UsuarioService usuarioService;

	// GET e POST para adicionar Funcionario
	
	@GetMapping("/adicionar")
	public String adicionarFuncionario(Model model) throws Exception {

		model.addAttribute("funcionario", new Funcionario());

		return "admin/funcionario/adicionarFuncionarios";
	}
	
	@PostMapping("/cadastrarGerencia")
	public String cadastrarGerencia(@Valid @ModelAttribute Funcionario funcionario, BindingResult resultado) throws Exception{

		if (resultado.hasErrors()) {
			return "admin/funcionario/adicionarFuncionarios";
		} else {
			
			funcionarioService.adicionarFuncionario(	funcionario.getUsuario().getEmail(), funcionario.getUsuario().getSenha(), 
														funcionario.getNome(), funcionario.getCpf(), funcionario.getTelefone(), 
														funcionario.getFuncao(), funcionario.getSalario(), funcionario.getDataAdimissao());

			return "redirect:/funcionario/listar";
		}
	}
	
	
	// GET e PUT para edita funcionarios
	
	@GetMapping("/atualizar/{id}")
	public String editarFuncionario(@PathVariable("id") Long id, Model model) throws Exception{

	    Funcionario funcionario = funcionarioService.buscarFuncionario(id).orElse(null);
	    model.addAttribute("funcionario", funcionario);
	    return "admin/funcionario/editarFuncionarios";
	}
	
	
	@PostMapping("/atualizar/{id}")
	public String atualizarFuncionario(@PathVariable("id") Long id, @Valid @ModelAttribute Funcionario funcionario, BindingResult resultado) throws Exception{

		String senha = funcionario.getUsuario().getSenha();
		
		Funcionario funcionario2 = funcionarioService.buscarFuncionario(id).orElse(null);
		
		if (senha.isEmpty()) {

			senha = funcionario2.getUsuario().getSenha();
		}
		
		if (resultado.hasErrors()) {
	
			return "admin/funcionario/editarFuncionarios";
		} else {
			
			funcionarioService.atualizarFuncionario(    id, funcionario.getUsuario().getEmail(), senha, 
														funcionario.getNome(), funcionario.getCpf(), funcionario.getTelefone(), 
														funcionario.getFuncao(), funcionario.getSalario(), funcionario.getDataAdimissao());
			
			return "redirect:/funcionario/listar";
			
		}
	}
	
	
	@GetMapping("/listar")
	public String listarFuncionarios(Model model) throws Exception {

		model.addAttribute("listar_funcionarios", funcionarioService.listarFuncionarios());

		return "admin/funcionario/listarFuncionarios";
	}	
	
	
	@GetMapping("/deletarGerencia/{id}")
	public String deletarFuncionarios(@PathVariable("id") Long id) throws Exception {
		
		funcionarioService.deletarFuncionario(id);
		return "redirect:/funcionario/listar";
	}
	

	


}
