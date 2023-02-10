package it.uniroma3.siw.yhop.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.yhop.model.Birrificio;
import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.repository.BirrificioRepository;
import it.uniroma3.siw.yhop.repository.PubRepository;

@Service
public class BirrificioService {

	@Autowired
BirrificioRepository breweryrepository;

	public List<Birrificio> findAll() {
		List<Birrificio> breweries=new ArrayList<>();
		for(Birrificio b: breweryrepository.findAll()) {
			breweries.add(b);
		}
		return breweries;
	}
	public int countAll() {
		int contatore=0;
		for(Birrificio b: this.findAll()) {
			contatore++;
		}
		return contatore;
	}
	public boolean alreadyExists(Birrificio target) {
		return this.breweryrepository.existsByNomeAndNazione(target.getNome(), target.getNazione());

	}
	public void addBirrificio(@Valid Birrificio birrificio) {
		this.breweryrepository.save(birrificio);
		
	}
	public Birrificio findById(Long id) {
		return breweryrepository.findById(id).orElse(null);
	}
}
