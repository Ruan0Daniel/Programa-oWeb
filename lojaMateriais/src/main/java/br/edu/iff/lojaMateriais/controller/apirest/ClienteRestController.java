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

import br.edu.iff.lojaMateriais.model.Cliente;
import br.edu.iff.lojaMateriais.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/clientes")
public class ClienteRestController {

	private final ClienteService clienteService;

	public ClienteRestController(ClienteService clienteService) throws Exception{
		this.clienteService = clienteService;
	}

	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar cliente.")
	public String adicionarCliente(String email, String senha, String nome, String cpf, String telefone) throws Exception{

		return clienteService.adicionarCliente(email, senha, nome, cpf, telefone);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar cliente.")
	public String atualizarCliente(@PathVariable("id") Long id, @RequestParam(required = false) String email,
			@RequestParam(required = false) String senha, @RequestParam(required = false) String nome,
			@RequestParam(required = false) String cpf, @RequestParam(required = false) String telefone) throws Exception{

		return clienteService.atualizarCliente(id, email, senha, nome, cpf, telefone);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar cliente.")
	public String deletarCliente(@PathVariable("id") Long id) throws Exception{
		return clienteService.deletarCliente(id);
	}

	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar clientes.")
	public List<Cliente> listarClientes() throws Exception{
		return clienteService.listarClientes();
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Obter cliente.")
	public Optional<Cliente> buscarCliente(@PathVariable("id") Long idCliente) throws Exception{
		return clienteService.buscarCliente(idCliente);
	}

}
