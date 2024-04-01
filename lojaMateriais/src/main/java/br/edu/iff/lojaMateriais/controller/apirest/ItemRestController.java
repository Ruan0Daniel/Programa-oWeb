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

import br.edu.iff.lojaMateriais.model.Item;
import br.edu.iff.lojaMateriais.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/v1/items")
public class ItemRestController {

	private final ItemService itemService;

	public ItemRestController(ItemService itemService) throws Exception{
		this.itemService = itemService;
	}

	@PostMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Adicionar item.")
	public String adicionarItem(@PathVariable("id") Long idProduto, int quatidade) throws Exception{
		
		return itemService.salvarItem(idProduto, quatidade);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar item.")
	public String atualizarItem(@PathVariable("id") Long idItem, @RequestParam(required = false) Long idProduto, @RequestParam(required = false) Integer quantidade) throws Exception{

		return itemService.atualizarItem(idItem, idProduto, quantidade);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar item.")
	public String deletarProduto(@PathVariable Long idItem) throws Exception{

		return itemService.deletarItem(idItem);
	}

	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar itens.")
	public List<Item> listarItems() throws Exception{

		return itemService.listarItems();
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Buscar item.")
	public Optional<Item> buscarProduto(@PathVariable("id") Long idItem) throws Exception{

		return itemService.obterItemPorId(idItem);
	}

}
