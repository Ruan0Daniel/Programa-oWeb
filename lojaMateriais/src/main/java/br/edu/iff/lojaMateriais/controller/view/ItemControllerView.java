package br.edu.iff.lojaMateriais.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.lojaMateriais.model.Cliente;
import br.edu.iff.lojaMateriais.model.Item;
import br.edu.iff.lojaMateriais.service.CarrinhoService;
import br.edu.iff.lojaMateriais.service.CarteiraService;
import br.edu.iff.lojaMateriais.service.ClienteService;
import br.edu.iff.lojaMateriais.service.CompraService;
import br.edu.iff.lojaMateriais.service.ItemService;
import br.edu.iff.lojaMateriais.service.ProdutoService;
import br.edu.iff.lojaMateriais.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "item")
public class ItemControllerView {

	@Autowired
	public ItemService itemService;


	@PostMapping("/editar/{idItem}")
	public String atualizarItem(@PathVariable("idItem") Long idItem, @ModelAttribute Item item, HttpSession session, HttpServletRequest request) throws Exception {
		
		System.out.print("ID item: " + item.getId());
	    
		itemService.atualizarItem(idItem, null, item.getQuantidade());
	    
		return "gerenciarDashboard";
	}
	
}
