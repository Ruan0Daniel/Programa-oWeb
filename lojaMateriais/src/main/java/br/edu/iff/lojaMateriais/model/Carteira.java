package br.edu.iff.lojaMateriais.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Carteira implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "saldoDisponivel")
	@PositiveOrZero(message="O saldo precisa ser maior ou igual a 0 (zero).")
	private float saldoDisponivel;

	public Carteira() {
		this.saldoDisponivel = 0;
	}

	public Long getId() {
		return id;
	}

	public void atualizarSaldo(float valor) {

		this.saldoDisponivel = valor;
	}

	public float getSaldoDisponivel() {
		return saldoDisponivel;
	}

	public void setSaldoDisponivel(float saldoDisponivel) {
		this.saldoDisponivel = saldoDisponivel;
	}

}
