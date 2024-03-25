package br.edu.iff.lojaMateriais.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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

import br.edu.iff.lojaMateriais.model.Produto;
import br.edu.iff.lojaMateriais.service.ProdutoService;
import br.edu.iff.lojaMateriais.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "produto")
public class ProdutoControllerView {

	@Autowired
	public ProdutoService produtoService;

	@GetMapping("/adicionar")
	public String adicionarCliente(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("adicionar_produto", new Produto());

		String resposta = request.getParameter("resposta");

		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "gerenciarProdutos";
	}
	

	@GetMapping("/listar")
	public String listarClientes(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("listar_produtos", produtoService.listarProdutos());

		return "gerenciarProdutos";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCliente(@PathVariable("id") Long id, Model model) throws Exception {

	    Produto produto = produtoService.obterProdutoPorId(id).orElse(null);
	    model.addAttribute("editar_produto", produto);
	    return "gerenciarProdutos";
	}
	
	@GetMapping("/deletarGerencia/{id}")
	public String deletarCliente(@PathVariable("id") Long id, Model model) throws Exception {
		
		produtoService.deletarProduto(id);
		return "gerenciarDashboard";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrarGerencia(@ModelAttribute Produto produto, BindingResult resultado, Model model) {

		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = produtoService.salvarProduto(produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidadeEstoque());
					
			try {
				return "redirect:/gerencia?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}
	
	@PostMapping("/atualizar/{id}")
	public String atualizarCliente(@PathVariable("id") Long id, @ModelAttribute Produto produto, BindingResult resultado, Model model) {

		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		} else {
			String resposta = produtoService.atualizarProduto(id, produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidadeEstoque());
			
			try {
				return "redirect:/gerencia?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "error";
			}
		}
	}

}
