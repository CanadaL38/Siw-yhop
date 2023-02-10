package it.uniroma3.siw.yhop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.yhop.model.Birra;
import it.uniroma3.siw.yhop.model.Birrificio;
import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.repository.BirraRepository;


@Service
public class BirraService {
	@Autowired
	private BirraRepository beerepository;
	
	public List<Birra> findAll() {
		List<Birra> beers=new ArrayList<>();
		for(Birra b: beerepository.findAll()) {
			beers.add(b);
		}
		return beers;
	}
	public int countAll() {
		int contatore=0;
		for(Birra b: this.findAll()) {
			contatore++;
		}
		return contatore;
	}
	public boolean alreadyExists(Birra target) {
		return this.beerepository.existsByNomeAndBirrificio(target.getNome(), target.getBirrificio());
	}
	public void aggiungiBirra(@Valid Birra birra) {
		this.beerepository.save(birra);
		
	}
	public Birra findById(Long id) {
		// TODO Auto-generated method stub
		return this.beerepository.findById(id).orElse(null);
	}
	

}
