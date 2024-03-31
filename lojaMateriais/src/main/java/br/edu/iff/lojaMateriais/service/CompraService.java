package br.edu.iff.lojaMateriais.service;

import br.edu.iff.lojaMateriais.model.Carteira;
import br.edu.iff.lojaMateriais.model.Cliente;
import br.edu.iff.lojaMateriais.model.Compra;
import br.edu.iff.lojaMateriais.model.Item;
import br.edu.iff.lojaMateriais.model.Produto;
import br.edu.iff.lojaMateriais.repository.CarteiraRepository;
import br.edu.iff.lojaMateriais.repository.CompraRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

	private final CompraRepository compraRepository;
	private final CarteiraRepository carteiraRepository;
	private final ClienteService clienteService;
	private final CarrinhoService carrinhoService;
	private final CarteiraService carteiraService;

	public CompraService(CompraRepository compraRepository, ClienteService clienteService,
			CarrinhoService carrinhoService, CarteiraService carteiraService, CarteiraRepository carteiraRepository) {
		this.compraRepository = compraRepository;
		this.carteiraRepository = carteiraRepository;
		this.clienteService = clienteService;
		this.carrinhoService = carrinhoService;
		this.carteiraService = carteiraService;
		
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
	
	public String atualizarCompra(Long id, String dataHora, Float valorFinal) {

		Compra compra = compraRepository.findById(id).orElse(null);

		if (compra == null) 
		{
			return "Produto não encontrado.";
		} 
		else 
		{

			if (dataHora != null) {
				compra.setDataHora(dataHora);
			}

			if (valorFinal != null) {
				compra.setPrecoFinal(valorFinal);
			}

			compraRepository.save(compra); // Salva as alterações no banco de dados.

			return "Atualizado com sucesso.";
		}
	}

	public String finalizarCompra(Long idCliente) {
	   
		Cliente cliente = clienteService.buscarCliente(idCliente).get();
		
		List<Item> listaProdutos = new ArrayList<>(cliente.getCarrinho().getListaDeItems());
		
		Compra compra = new Compra(cliente, listaProdutos);

	    Carteira carteira = cliente.getCarteira();
	    
	        
	        if (cliente != null) {
	           
	        	if (!compra.getVendaFinalizada()) {
	            	
	        		if(cliente.getCarteira().getSaldoDisponivel() >= cliente.getCarrinho().getValorTotal())
	            	{
	            		LocalDateTime dataHoraAtual = LocalDateTime.now();
	 	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	 	                String dataHoraFormatada = dataHoraAtual.format(formatter);
	 	                
	 	                compra.setDataHora(dataHoraFormatada);
	 	                compra.setVendaFinalizada(true);
	 	                
	 	                cliente.getCarrinho().getListaDeItems().clear();
	 	          
	 	                
	 	                cliente.getCarteira().atualizarSaldo(-cliente.getCarrinho().getValorTotal());	 	            
	 	                
	 	                compra.setPrecoFinal(cliente.getCarrinho().getValorTotal());
	 	                cliente.getCarrinho().setValorTotal(0);
	 	                
	 	                carrinhoService.atualizarCarrinho(cliente.getCarrinho());
	 	                carteiraService.atualizarCarteira(cliente.getCarteira());
	 	                
	 	                compraRepository.save(compra);
	 	                carteiraRepository.save(carteira);
	 	                
	 	                return "Venda Finalizada";
	            		
	            	} else { return "Saldo insuficiente";}         
	               
	            } else {
	                return "A venda já foi finalizada";
	            }
	        } else {
	            return "Cliente não encontrado";
	        }
	    } 
}

