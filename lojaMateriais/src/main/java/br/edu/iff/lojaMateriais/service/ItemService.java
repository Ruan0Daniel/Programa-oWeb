package br.edu.iff.lojaMateriais.service;

import br.edu.iff.lojaMateriais.model.Item;
import br.edu.iff.lojaMateriais.model.Produto;
import br.edu.iff.lojaMateriais.repository.ItemRepository;
import br.edu.iff.lojaMateriais.repository.ProdutoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

	private final ItemRepository itemRepository;
	private final ProdutoRepository produtoRepository;
	

	public ItemService(ItemRepository itemRepository, ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
		this.itemRepository = itemRepository;
	}

	public List<Item> listarItems() {
		return itemRepository.findAll();
	}

	public Optional<Item> obterItemPorId(Long id) {
		return itemRepository.findById(id);
	}

	public String salvarItem(Long idProduto, Integer quantidade) {
		
		Produto produto = produtoRepository.findById(idProduto).orElse(null);
		
		if(produto != null)
		{
			Item item = new Item(produto, quantidade);
			itemRepository.save(item);

			return "Operação realizada.";
			
		}

		return "Operação não realizada";
	}

	public String deletarItem(Long id) {

		if (itemRepository.findById(id).isEmpty() == true) {
			return "Item não encontrado.";
		} else {
			itemRepository.deleteById(id);

			return "Item deletado com sucesso!";
		}
	}

	public String atualizarItem(Long idItem, Long IdProduto, Integer quantidade) {

		Item item = itemRepository.findById(idItem).orElse(null);
		Produto produto = produtoRepository.findById(IdProduto).orElse(null);

		if (item == null) {
			return "Item não encontrado.";
		} else {

			if (item != null) {
				item.setProduto(produto);
			}

			if (quantidade != null) {
				item.setQuantidade(quantidade);
			}

			itemRepository.save(item); // Salva as alterações no banco de dados.

			return "Atualizado com sucesso.";
		}
	}
}