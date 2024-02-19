package br.edu.iff.lojaMateriais.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "datahora")
	@PastOrPresent(message="Não pode ser no futuro")
	private String dataHora;

	@Column(name = "precoFinal")
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
	private float precoFinal;

	@Column(name = "vendaConluida")
	private Boolean vendaFinalizada;

	@OneToMany
	@JoinColumn(name = "listaProdutos")
	private List<ProdutoCompra> listaDeProdutosCompra = null;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Compra(Cliente cliente, List<Produto> listaDeProdutos) {
		this.cliente = cliente;
		preencherListaProdutosCompra(listaDeProdutos);
		this.vendaFinalizada = false;
	}

	public Compra() {
		// construtor padrão vazio
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public float getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(float precoFinal) {
		this.precoFinal = precoFinal;
	}

	public Boolean getVendaFinalizada() {
		return vendaFinalizada;
	}

	public void setVendaFinalizada(Boolean vendaFinalizada) {
		this.vendaFinalizada = vendaFinalizada;
	}

	public List<ProdutoCompra> getListaDeProdutosCompra() {
		return listaDeProdutosCompra;
	}

	public void setListaDeProdutosCompra(List<ProdutoCompra> listaDeProdutosCompra) {
		
		this.listaDeProdutosCompra = new ArrayList<>(listaDeProdutosCompra);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public void preencherListaProdutosCompra(List<Produto> listaDeProdutos) {

		List<ProdutoCompra> listaProdutosCompra = new ArrayList<>();
		
		float precoFinal = 0;

		if (listaDeProdutos != null ) {
			for (Produto produto : listaDeProdutos) {
				
				ProdutoCompra produtoCompra = new ProdutoCompra();

				produtoCompra.setNome(produto.getNome());
				produtoCompra.setDescricao(produto.getDescricao());
				produtoCompra.setPreco(produto.getPreco());
				produtoCompra.setQuantidadeAdquirida(1);

				precoFinal = precoFinal + produto.getPreco();
				
				listaProdutosCompra.add(produtoCompra);
			}
			
			this.precoFinal = precoFinal;
		}

	}

}
