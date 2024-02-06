package br.edu.iff.lojaMateriais.controller.apirest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.lojaMateriais.model.Funcionario;
import br.edu.iff.lojaMateriais.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/funcionarios")
public class FuncionarioRestController {

	private final FuncionarioService funcionarioService;

	public FuncionarioRestController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar funcionario.")
	public String adicionarFuncionario(String email, String senha, String nome, String cpf, String telefone,
			String funcao, Float salario, String dataAdmissao) {

		return funcionarioService.adicionarFuncionario(email, senha, nome, cpf, telefone, funcao, salario,
				dataAdmissao);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar funcionario.")
	public String atualizarFuncionario(@PathVariable("id") Long idFuncionario,
			@RequestParam(required = false) String email, @RequestParam(required = false) String senha,
			@RequestParam(required = false) String nome, @RequestParam(required = false) String cpf,
			@RequestParam(required = false) String telefone, @RequestParam(required = false) String funcao,
			@RequestParam(required = false) Float salario, @RequestParam(required = false) String dataAdmissao) {

		return funcionarioService.atualizarFuncionario(idFuncionario, email, senha, nome, cpf, telefone, funcao,
				salario, dataAdmissao);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar funcionario.")
	public String deletarFuncionario(@PathVariable("id") Long idFuncionario) {
		return funcionarioService.deletarFuncionario(idFuncionario);
	}

	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar funcionarios.")
	public List<Funcionario> listarFuncionarios() {
		return funcionarioService.listarFuncionarios();
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Obter funcionario.")
	public Optional<Funcionario> buscarFuncionario(@PathVariable("id") Long idFuncionario) {
		return funcionarioService.buscarFuncionario(idFuncionario);
	}
}
