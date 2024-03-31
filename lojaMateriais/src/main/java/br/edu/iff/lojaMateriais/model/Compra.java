package br.edu.iff.lojaMateriais.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "datahora")
	private String dataHora;

	@Column(name = "precoFinal")
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
	private float precoFinal;

	@Column(name = "vendaConluida")
	private Boolean vendaFinalizada;

	@OneToMany
	@JoinColumn(name = "listaProdutos")
	private List<Item> listaDeProdutos = null;

	@ManyToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	public Compra(Cliente cliente, List<Item> listaDeProdutos) {
		this.cliente = cliente;
		
		this.listaDeProdutos = listaDeProdutos;
		//preencherListaProdutosCompra(listaDeProdutos);
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

	public List<Item> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(List<Item> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	/*public void preencherListaProdutosCompra(List<Produto> listaDeProdutos) {

		List<Item> listaProdutosCompra = new ArrayList<>();
		
		float precoFinal = 0;

		if (listaDeProdutos != null ) {
			for (Produto produto : listaDeProdutos) {
				
				Item produtoCompra = new Item();

				produtoCompra.setNome(produto.getNome());
				produtoCompra.setDescricao(produto.getDescricao());
				produtoCompra.setPreco(produto.getPreco());
				produtoCompra.setQuantidadeAdquirida(1);

				precoFinal = precoFinal + produto.getPreco();
				
				listaProdutosCompra.add(produtoCompra);
			}
			
			this.precoFinal = precoFinal;
		}

	}*/

}
