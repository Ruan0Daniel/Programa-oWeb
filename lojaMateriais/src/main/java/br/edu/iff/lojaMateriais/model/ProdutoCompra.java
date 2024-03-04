package br.edu.iff.lojaMateriais.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class ProdutoCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	@NotEmpty
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "preco")
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
	private Float preco;

	@Column(name = "quantidadeAdquirida")
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
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
