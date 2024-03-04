package br.edu.iff.lojaMateriais.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


@Entity 
public class Produto implements  Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	@Column(name = "nome")
	@Size(min=1,max=60,message="Tem que ter entre 1 e 60 caractéres")
	private String nome;
	
	@Column(name = "descricao")
	@Size(min=1,max=512,message="Tem que ter entre 1 e 512 caractéres")
	private String descricao;
	
	@Column(name = "preco")
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
	private Float preco;
	
	@Column(name = "quantidade")
	@PositiveOrZero(message="Tem que ser maior ou igual a 0")
	private Integer quantidadeEstoque;
	
	public Produto(String nome, String descricao, Float preco, Integer quantidadeEstoque) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public Produto() {
        
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

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

}
