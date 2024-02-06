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

@Entity
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany // (cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "produtos")
	private List<Produto> listaDeProdutos;

	public void adicionarProduto(Produto produto) {

		if (listaDeProdutos == null) {
			listaDeProdutos = new ArrayList<>();
		}

		listaDeProdutos.add(produto);
	}

	public void removerProduto(Produto produto) {

		if (listaDeProdutos != null) {
			listaDeProdutos.remove(produto);
		}
	}

	public float calcularTotal() {

		float total = 0;

		if (listaDeProdutos != null) {
			for (Produto produto : listaDeProdutos) {
				total += produto.getPreco();
			}
		}

		return total;
	}

	public Carrinho() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
}
