package br.edu.iff.lojaMateriais.service;

import br.edu.iff.lojaMateriais.model.Usuario;
import br.edu.iff.lojaMateriais.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> obterUsuario(Long id) {

		return usuarioRepository.findById(id);
	}

	public String deletarUsuario(Long id) {

		if (usuarioRepository.findById(id).isEmpty() == true) {
			return "Usuario não encontrado";
		} else {
			usuarioRepository.deleteById(id);

			return "Usuario deletado com sucesso!";
		}
	}

	public String criarUsuario(String email, String senha) {

		Usuario usuario = new Usuario(email, senha, 0);
		usuarioRepository.save(usuario);

		return "Usuario criado com sucesso.";
	}

	@Transactional
	public String atualizarUsuario(Long id, String email, String senha/* , Integer nivelAcesso */) {

		Usuario usuario = usuarioRepository.findById(id).orElse(null);

		if (usuario == null) {
			return "Usuario não encontrado.";
		} else {

			if (email != null) {
				usuario.setEmail(email);
			}

			if (senha != null) {
				usuario.setSenha(senha);
			}

			/*
			 * if (nivelAcesso != null) { usuario.setNivelAcesso(nivelAcesso); }
			 */

			usuarioRepository.save(usuario); // Salva as alterações no banco de dados.

			return "Usuario atualizado com sucesso.";
		}
	}

}