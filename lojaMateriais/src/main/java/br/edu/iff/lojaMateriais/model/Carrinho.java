package br.edu.iff.lojaMateriais.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@PositiveOrZero(message = "O valor total deve ser maior ou igual a 0")
	private float valorTotal = 0;

	@OneToMany // (cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "produtos")
	private List<Item> listaDeItems;

	public void adicionarProduto(Item item) {

		if (listaDeItems == null) {
			listaDeItems = new ArrayList<>();
		}

		this.valorTotal = this.valorTotal + (item.getProduto().getPreco() * item.getQuantidade());
		
		listaDeItems.add(item);
	}

	public void removerProduto(Item item) {

		if (listaDeItems != null) {
			listaDeItems.remove(item);
		}
		
		this.valorTotal = this.valorTotal - (item.getProduto().getPreco() * item.getQuantidade());
	}

	public float calcularTotal() {

		float total = 0;
		int quantidade = 0;

		if (listaDeItems != null) {
			for (Item item : listaDeItems) {
				total += item.getProduto().getPreco() * item.getQuantidade();
				quantidade += 1;
			}
		}

		return quantidade;
	}

	public Carrinho() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getListaDeItems() {
		return listaDeItems;
	}

	public void setListaDeItems(List<Item> listaDeItems) {
		this.listaDeItems = listaDeItems;
	}
	
	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}


}
