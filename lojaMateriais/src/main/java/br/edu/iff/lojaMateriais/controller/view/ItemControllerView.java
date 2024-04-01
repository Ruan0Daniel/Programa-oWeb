package br.edu.iff.lojaMateriais.controller.view;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.lojaMateriais.model.Item;
import br.edu.iff.lojaMateriais.service.ItemService;

@Controller
@RequestMapping(path = "item")
public class ItemControllerView {

	@Autowired
	public ItemService itemService;


	@PostMapping("/editar/{idItem}")
	public String atualizarItem(@PathVariable("idItem") Long idItem, @ModelAttribute Item item) throws Exception {
		
		System.out.print("ID item: " + item.getId());
	    
		itemService.atualizarItem(idItem, null, item.getQuantidade());
	    
		return "gerenciarDashboard";
	}
	
}
