package br.edu.iff.lojaMateriais.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	@Size(min=3,max=60,message="O nome precisa conter pelo menos 3 caracteres e no máximo 60.")
	private String nome;

	@Column(name = "cpf")
	@NotBlank(message="O CPF não pode ser nulo.")
	@Pattern(regexp="[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}", message="O CPF informado deve estar conforme o padrão, ex: 123.456.789-00")
	private String cpf;

	@Column(name = "telefone")
	@Size(min=8,max=20,message="Precisa conter pelo menos 8 dígitos e no máximo 20.")
	private String telefone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Pessoa(String email, String senha, String nome, String cpf, String telefone, Integer nivelAcesso) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;

		Usuario usuario = new Usuario(email, senha, nivelAcesso);

		this.usuario = usuario;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
