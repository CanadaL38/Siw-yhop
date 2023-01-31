package it.uniroma3.siw.yhop.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;



@Entity
public class Birra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@NotBlank
	private Long gradazione;
	
	@ManyToOne
	private Birrificio birrificio;
	
	@ManyToOne
	private Taplist taplist;
	
	public Birra() {
		this(null,null,null);
	}
	
	public Birra(String nome, String descrizione, Long gradazione) {
		this.nome=nome;
		this.descrizione=descrizione;
		this.gradazione=gradazione;
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

	public Long getGradazione() {
		return gradazione;
	}

	public void setGradazione(Long gradazione) {
		this.gradazione = gradazione;
	}

	public Birrificio getBirrificio() {
		return birrificio;
	}

	public void setBirrificio(Birrificio birrificio) {
		this.birrificio = birrificio;
	}

	public Taplist getTaplist() {
		return taplist;
	}

	public void setTaplist(Taplist taplist) {
		this.taplist = taplist;
	}
	
	
}
