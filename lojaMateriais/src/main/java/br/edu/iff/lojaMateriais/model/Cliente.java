package br.edu.iff.lojaMateriais.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cliente")
@JsonPropertyOrder({ "id", "nome", "cpf", "telefone", "usuario", "carteira", "carrinho" })
public class Cliente extends Pessoa{

	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carteira_id")
	private Carteira carteira;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carrinho_id")
	private Carrinho carrinho;

	public Cliente(String email, String senha, String nome, String cpf, String telefone, Carteira novaCarteira,
			Carrinho novoCarrinho) {

		super(email, senha, nome, cpf, telefone, 0);

		this.carteira = novaCarteira;
		this.carrinho = novoCarrinho;
	}

	public Cliente() {
		super(null, null, null, null, null, null);
		this.carteira = new Carteira();
		this.carrinho = new Carrinho();
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

}
