package br.edu.iff.lojaMateriais.service;

import org.springframework.stereotype.Service;

import br.edu.iff.lojaMateriais.repository.ClienteRepository;
import br.edu.iff.lojaMateriais.model.Cliente;
import br.edu.iff.lojaMateriais.model.Carrinho;
import br.edu.iff.lojaMateriais.model.Carteira; // Adicione a importação da classe Carteira

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final CarteiraService carteiraService;
	private final CarrinhoService carrinhoService;
	private final UsuarioService usuarioService;

	public ClienteService(ClienteRepository clienteRepository, CarteiraService carteiraService,
			CarrinhoService carrinhoService, UsuarioService usuarioService) {
		this.clienteRepository = clienteRepository;
		this.carteiraService = carteiraService;
		this.carrinhoService = carrinhoService;
		this.usuarioService = usuarioService;
	}

	public String adicionarCliente(String email, String senha, String nome, String cpf, String telefone) {

		Carrinho novoCarrinho = carrinhoService.criarCarrinho();

		Carteira novaCarteira = carteiraService.criarCarteira();

		Cliente novoCliente = new Cliente(email, senha, nome, cpf, telefone, novaCarteira, novoCarrinho);

		clienteRepository.save(novoCliente);

		return "Cliente adicionado com sucesso.";
	}

	public String deletarCliente(Long id) {

		Cliente cliente = clienteRepository.findById(id).orElse(null);
		
		if (cliente == null) {
			return "Cliente não encontrado";
		} else {
			clienteRepository.deleteById(id);

			/*
			 * Long idCompra = cliente.getCompra().getId();
			 * carrinhoService.deletarCarrinho(idCarrinho);
			 */
			
			Long idCarrinho = cliente.getCarrinho().getId();
			carrinhoService.deletarCarrinho(idCarrinho);

			Long idUsuario = cliente.getUsuario().getId();
			usuarioService.deletarUsuario(idUsuario);

			Long idCarteira = cliente.getCarteira().getId();
			carteiraService.deletarCarteira(idCarteira);

			return "Cliente deletado com sucesso.";
		}
	
	}

	public List<Cliente> listarClientes() {

		return clienteRepository.findAll();
	}

	public Optional<Cliente> buscarCliente(Long id) {

		return clienteRepository.findById(id);
	}

	public String atualizarCliente(Long id, String email, String senha, String nome, String cpf, String telefone) {

		Cliente cliente = clienteRepository.findById(id).get();

		Long idUsuario = cliente.getUsuario().getId();

		if (cliente == null) {
			return "Cliente não encontrado.";
		}

		else {

			usuarioService.atualizarUsuario(idUsuario, email, senha);

			if (nome != null) {
				cliente.setNome(nome);
			}

			if (cpf != null) {
				cliente.setCpf(cpf);
			}

			if (telefone != null) {
				cliente.setTelefone(telefone);
			}

			clienteRepository.save(cliente); // Salva as alterações no banco de dados.

			return "Atualizado com sucesso.";
		}
	}

	/*
	 * public Carrinho obterCarrinhoDoCliente(Long idCliente) { Cliente cliente =
	 * buscarCliente(idCliente);
	 * 
	 * if (cliente != null && cliente.getCarrinho().isPresent()) { return
	 * cliente.getCarrinho().get(); } else { return null; }
	 * 
	 * }
	 */
}