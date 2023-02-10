package it.uniroma3.siw.yhop.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;



@Entity
public class Pub {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String citta;

	@NotBlank
	private String indirizzo;
	
	@OneToOne
	private Taplist taplist;
	
	public Pub() {
		this(null,null,null);
	}
	public Pub(String nome, String indirizzo, String citta ) {
		this.nome=nome;
		this.indirizzo=indirizzo;
		this.citta=citta;
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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}

	public Taplist getTaplist() {
		return taplist;
	}

	public void setTaplist(Taplist taplist) {
		this.taplist = taplist;
	}

	
	
}
