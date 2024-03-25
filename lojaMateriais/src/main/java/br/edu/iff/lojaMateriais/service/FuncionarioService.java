package br.edu.iff.lojaMateriais.service;

import br.edu.iff.lojaMateriais.model.Funcionario;
import br.edu.iff.lojaMateriais.repository.FuncionarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

	private final FuncionarioRepository funcionarioRepository;
	private final UsuarioService usuarioService;

	public FuncionarioService(FuncionarioRepository funcionarioRepository, UsuarioService usuarioService) {

		this.funcionarioRepository = funcionarioRepository;
		this.usuarioService = usuarioService;
	}

	public String adicionarFuncionario(String email, String senha, String nome, String cpf, String telefone, String funcao, Float salario, String dataAdmissao) {

		Funcionario novoFuncionario = new Funcionario(email, senha, nome, cpf, telefone, funcao, salario, dataAdmissao);

		funcionarioRepository.save(novoFuncionario);

		return "Funcionario adicionado com sucesso.";
	}

	public String deletarFuncionario(Long id) {

		Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);

		if (funcionario == null) {
			return "Funcionario não encontrado";
		} else {
			funcionarioRepository.deleteById(id);

			return "Funcionario deletado com sucesso.";
		}
	}

	public List<Funcionario> listarFuncionarios() {

		return funcionarioRepository.findAll();
	}

	public Optional<Funcionario> buscarFuncionario(Long id) {

		return funcionarioRepository.findById(id);
	}

	public String atualizarFuncionario(Long id, String email, String senha, String nome, String cpf, String telefone,
			String funcao, Float salario, String dataAdmissao) {

		Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);

		Long idUsuario = funcionario.getUsuario().getId();

		if (funcionario == null) {
			return "Funcionario não encontrado.";
		}

		else {

			usuarioService.atualizarUsuario(idUsuario, email, senha);

			if (nome != null) {
				funcionario.setNome(nome);
			}

			if (cpf != null) {
				funcionario.setCpf(cpf);
			}

			if (telefone != null) {
				funcionario.setTelefone(telefone);
			}

			if (funcao != null) {
				funcionario.setFuncao(funcao);
			}

			if (salario != null) {
				funcionario.setSalario(salario);
			}

			if (dataAdmissao != null) {
				funcionario.setDataAdimissao(dataAdmissao);
			}

			funcionarioRepository.save(funcionario); // Salva as alterações no banco de dados.

			return "Atualizado com sucesso.";
		}
	}

}