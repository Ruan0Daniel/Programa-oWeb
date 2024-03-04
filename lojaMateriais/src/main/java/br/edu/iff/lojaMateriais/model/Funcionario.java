package br.edu.iff.lojaMateriais.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Funcionario")
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(name = "funcao")
	@NotEmpty
	private String funcao;

	@Column(name = "salario")
	@Positive(message="O saldo precisa ser maior do que 0 (zero).")
	private float salario;

	@Column(name = "data")
	private String dataAdimissao;

	public Funcionario(String email, String senha, String nome, String cpf, String telefone, String funcao, float salario, String dataAdimissao) {

		super(email, senha, nome, cpf, telefone, 1);

		this.funcao = funcao;
		this.salario = salario;
		this.dataAdimissao = dataAdimissao;
	}
	
	public Funcionario() {
		super(null, null, null, null, null, null);
    }

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public String getDataAdimissao() {
		return dataAdimissao;
	}

	public void setDataAdimissao(String dataAdimissao) {
		this.dataAdimissao = dataAdimissao;
	}

}
