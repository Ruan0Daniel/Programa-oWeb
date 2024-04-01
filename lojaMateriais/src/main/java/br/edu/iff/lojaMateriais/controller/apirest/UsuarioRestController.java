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

import br.edu.iff.lojaMateriais.model.Usuario;
import br.edu.iff.lojaMateriais.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/usuarios")
public class UsuarioRestController {
	
    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) throws Exception{
        this.usuarioService = usuarioService;
    }
	
    @PostMapping("{Email}/{Senha}")
	@ResponseBody
	@Operation(summary = "Adicionar usuário.")
	public String adicionarUsuario(String email, String senha) throws Exception{
		
		return usuarioService.criarUsuario(email, senha);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar usuário.")
	public String atualizarUsuario( @PathVariable("id") Long id, 
									@RequestParam(required = false) String usuario, 
									@RequestParam(required = false) String senha/*,
									@RequestParam(required = false) Integer nivelAcesso */) throws Exception{
		
		return usuarioService.atualizarUsuario(id, usuario, senha/*, nivelAcesso*/);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar usuário.")
	public String deletarUsuario(@PathVariable("id") Long idUsuario) throws Exception{
		
		return usuarioService.deletarUsuario(idUsuario);
	}
	
	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar usuários.")
	public List<Usuario> listarUsuaruios()  throws Exception{
		
		return usuarioService.listarUsuarios();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Buscar usuário.")
	public Optional<Usuario> buscarUsuario(@PathVariable("id") Long idUsuario) throws Exception{
		
		return usuarioService.obterUsuario(idUsuario);
	}

}
