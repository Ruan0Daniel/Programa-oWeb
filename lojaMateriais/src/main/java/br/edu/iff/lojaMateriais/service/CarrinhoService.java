package br.edu.iff.lojaMateriais.service;

import br.edu.iff.lojaMateriais.model.Carrinho;
import br.edu.iff.lojaMateriais.model.Item;
import br.edu.iff.lojaMateriais.model.Produto;
import br.edu.iff.lojaMateriais.repository.CarrinhoRepository;
import br.edu.iff.lojaMateriais.repository.ItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

	private final CarrinhoRepository carrinhoRepository;
	private final ItemRepository itemRepository;
	private final ProdutoService produtoService;
	private final ItemService itemService;

	public CarrinhoService(CarrinhoRepository carrinhoRepository, ItemRepository itemRepository, ProdutoService produtoService, ItemService itemService) {
		
		this.carrinhoRepository = carrinhoRepository;
		this.itemRepository = itemRepository;
		this.produtoService = produtoService;
		this.itemService = itemService;
		
	}

	public List<Carrinho> listarCarrinhos() {
		return carrinhoRepository.findAll();
	}

	public Optional<Carrinho> obterCarrinho(Long id) {

		return carrinhoRepository.findById(id);
	}

	public String adicionarProdutoNoCarrinho(Long idCarrinho, Long idProduto, Integer quantidade) {

		Carrinho carrinho = obterCarrinho(idCarrinho).get();
		
		Produto produto = produtoService.obterProdutoPorId(idProduto).get();
		
		int quantidadeExistente = 0;
		
		for (Item item : carrinho.getListaDeItems()) { 
			
			if(idProduto == item.getProduto().getId())
			{
				quantidadeExistente = item.getQuantidade();
				item.setQuantidade(quantidade + quantidadeExistente);
				
				carrinho.adicionarProduto(item);
				carrinhoRepository.save(carrinho);		
				
				return "Item adicionado";
			}
			
		}

		if (carrinho == null) {
			return "Carrinho não encontrado";
		}

		if (produto == null) {
			return "Produto não encontrado";
		}
		
		
		Item item = new Item(produto, quantidade);
		
		itemRepository.save(item);
		
		carrinho.adicionarProduto(item);
		carrinhoRepository.save(carrinho);

		return "Operação realizada";
	}

	public String removerProdutoDoCarrinho(Long idCarrinho, Long idItem) {

		Optional<Carrinho> carrinhoOptional = obterCarrinho(idCarrinho);
		
		Optional<Item> itemOptional = itemService.obterItemPorId(idItem);

		if (carrinhoOptional.isEmpty()) {
			return "Carrinho não encontrado";
		}

		if (itemOptional.isEmpty()) {
			return "Produto não encontrado";
		}

		Carrinho carrinho = carrinhoOptional.get();
		Item item = itemOptional.get();

		carrinho.removerProduto(item);
		carrinhoRepository.save(carrinho);
		
		itemService.deletarItem(idItem);

		return "Operação realizada";
	}

	public String deletarCarrinho(Long id) {

		if (carrinhoRepository.findById(id).isEmpty() == true) {
			return "Carrinho não encontrado";
		} else {
			carrinhoRepository.deleteById(id);

			return "Carrinho deletado com sucesso!";
		}
	}

	public void atualizarCarrinho(Carrinho carrinho) {
		
		carrinhoRepository.save(carrinho);
	}

	public Carrinho criarCarrinho() {

		Carrinho carrinho = new Carrinho();
		carrinhoRepository.save(carrinho);

		return carrinho;
	}
	
	public String editarCarrinho(Long idCarrinho, Float valorFinal) {
		
		Carrinho carrinho = obterCarrinho(idCarrinho).orElse(null);
		
		if(carrinho != null && valorFinal != null)
		{
			carrinho.setValorTotal(valorFinal);
			carrinhoRepository.save(carrinho);
			
			return "Valor final atualizado";
		}

		return "Valor final não foi atualizado";
		
	}


}