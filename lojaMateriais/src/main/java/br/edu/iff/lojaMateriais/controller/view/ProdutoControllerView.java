package br.edu.iff.lojaMateriais.controller.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.lojaMateriais.model.Produto;
import br.edu.iff.lojaMateriais.service.ProdutoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "produto")
public class ProdutoControllerView {

	@Autowired
	public ProdutoService produtoService;

	// GET e POST para adicionar produto
	
	@GetMapping("/adicionar")
	public String adicionarCliente(Model model) throws Exception {

		model.addAttribute("produto", new Produto());

		return "admin/produto/adicionarProdutos";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrarGerencia(@Valid @ModelAttribute Produto produto, BindingResult resultado) throws Exception{

		if (resultado.hasErrors()) {
			return "admin/produto/adicionarProdutos";
		} else {
			produtoService.salvarProduto(produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidadeEstoque());
					
			return "redirect:/produto/listar";		
		}
	}
	
	// GET e POST para atualizar produto
	
	@GetMapping("/editar/{id}")
	public String editarCliente(@PathVariable("id") Long id, Model model) throws Exception {

	    Produto produto = produtoService.obterProdutoPorId(id).orElse(null);
	    model.addAttribute("produto", produto);
	    return "admin/produto/editarProdutos";
	}
	
	@PostMapping("/atualizar/{id}")
	public String atualizarCliente(@Valid @PathVariable("id") Long id, @ModelAttribute Produto produto, BindingResult resultado) throws Exception{

		if (resultado.hasErrors()) {
			return "admin/produto/editarProdutos";
		} else {
			produtoService.atualizarProduto(id, produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidadeEstoque());
			
			return "redirect:/produto/listar";	
		}
	}
	

	@GetMapping("/listar")
	public String listarClientes(Model model) throws Exception {

		model.addAttribute("listar_produtos", produtoService.listarProdutos());

		return "admin/produto/listarProdutos";
	}
	
	@GetMapping("/deletarGerencia/{id}")
	public String deletarCliente(@PathVariable("id") Long id) throws Exception {
		
		produtoService.deletarProduto(id);
		return "redirect:/produto/listar";
	}

}
