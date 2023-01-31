package it.uniroma3.siw.yhop.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Taplist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String descrizione;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Pub pub;
	@OneToMany(mappedBy = "taplist", fetch = FetchType.EAGER)
	protected List<Birra> birre;
	
	public Taplist() {
		this(null,null);
	}

	public Taplist(String nome, String descrizione) {
		this.nome=nome;
		this.descrizione=descrizione;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Pub getPub() {
		return pub;
	}

	public void setPub(Pub pub) {
		this.pub = pub;
	}

	public List<Birra> getBirre() {
		return birre;
	}

	public void setBirre(List<Birra> birre) {
		this.birre = birre;
	}

}
