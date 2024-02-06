package br.edu.iff.lojaMateriais.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Funcionario")
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(name = "funcao")
	private String funcao;

	@Column(name = "salario")
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
