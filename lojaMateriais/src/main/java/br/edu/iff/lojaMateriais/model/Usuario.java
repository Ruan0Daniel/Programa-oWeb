package br.edu.iff.lojaMateriais.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity 
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email")
	@Email(message="Tem que ser em formato de email")
	private String email;
	
	@Column(name = "senha")
	@Size(min=8,max=20,message="Tem que ter entre 8 e 20 caractéres")
	@NotEmpty // Não deve ser NULO e nem VAZIO.
	private String senha;
	
	@Column(name = "nivelAcesso")
	private Integer nivelAcesso;
	
	
	public Usuario(String email, String senha, Integer nivelAcesso) {
		this.email = email;
		this.senha = senha;
		this.nivelAcesso = nivelAcesso;
	}

	public Usuario() {

	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public int getNivelAcesso() {
		return nivelAcesso;
	}


	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	
}
