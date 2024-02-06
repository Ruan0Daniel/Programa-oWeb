package br.edu.iff.lojaMateriais.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProdutoCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "preco")
	private Float preco;

	@Column(name = "quantidadeAdquirida")
	private Integer quantidadeAdquirida;

	public ProdutoCompra(String nome, String descricao, Float preco, Integer quantidadeAdquirida) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidadeAdquirida = quantidadeAdquirida;
	}

	public ProdutoCompra() {
		// Construtor padrão sem argumentos
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Integer quantidadeAdquirida() {
		return quantidadeAdquirida;
	}

	public void setQuantidadeAdquirida(Integer quantidadeAdquirida) {
		this.quantidadeAdquirida = quantidadeAdquirida;
	}

}
