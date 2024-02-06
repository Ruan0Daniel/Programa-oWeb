package br.edu.iff.lojaMateriais.service;

import br.edu.iff.lojaMateriais.model.Cliente;
import br.edu.iff.lojaMateriais.model.Compra;
import br.edu.iff.lojaMateriais.model.Produto;
import br.edu.iff.lojaMateriais.repository.CompraRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

	private final CompraRepository compraRepository;
	private final ClienteService clienteService;
	private final CarrinhoService carrinhoService;
	private final CarteiraService carteiraService;

	public CompraService(CompraRepository compraRepository, ClienteService clienteService,
			CarrinhoService carrinhoService, CarteiraService carteiraService) {
		this.compraRepository = compraRepository;
		this.clienteService = clienteService;
		this.carrinhoService = carrinhoService;
		this.carteiraService = carteiraService;
	}

	
	public String criarCompra(Long idCliente) {

		Cliente cliente = clienteService.buscarCliente(idCliente).get();
		
		List<Produto> listaProdutos = cliente.getCarrinho().getListaDeProdutos();
		

		if (cliente != null) {

			Compra compra = new Compra(cliente, listaProdutos);

			compraRepository.save(compra);

			return "Compra criada com sucesso";
		} else {
			return "Cliente ou Carrinho não encontrado";
		}
	}

	public List<Compra> listarCompras() {
		return compraRepository.findAll();
	}

	public Optional<Compra> obterCompra(Long id) {

		return compraRepository.findById(id);
	}

	public String deletarCompra(Long id) {

		if (compraRepository.findById(id).isEmpty() == true) {
			return "Carrinho não encontrado";
		} else {
			compraRepository.deleteById(id);

			return "Carrinho deletado com sucesso!";
		}
	}

	public String confirmarCompra(Long idCompra) {

		Long idCliente = obterCompra(idCompra).get().getCliente().getId();
		
		Cliente cliente = clienteService.buscarCliente(idCliente).orElse(null);

		Compra compra = obterCompra(idCompra).get();
		
		compra.preencherListaProdutosCompra(cliente.getCarrinho().getListaDeProdutos()); // Preenche a lista de produtos

		if ((cliente.getCarteira().getSaldoDisponivel() - compra.getPrecoFinal() >= 0) && compra.getVendaFinalizada() == false) // Verifica se o cliente possui saldo na carteira e se a venda já foi finalizada																			
			{	
			
			LocalDateTime dataHoraAtual = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String dataHoraFormatada = dataHoraAtual.format(formatter);

			compra.setDataHora(dataHoraFormatada); // Salva a data e hora da compra

			compra.setVendaFinalizada(true); // Altera a venda para concluida

			cliente.getCarrinho().getListaDeProdutos().clear(); // Limpa o carrinho

			cliente.getCarteira().atualizarSaldo(-compra.getPrecoFinal()); // Desconta o valor da carteira

			carrinhoService.atualizarCarrinho(cliente.getCarrinho());

			carteiraService.atualizarCarteira(cliente.getCarteira());

			compraRepository.save(compra);

			return "Venda Finalizada";
		} else {
			return "Saldo insuficiente";
		}

	}

}