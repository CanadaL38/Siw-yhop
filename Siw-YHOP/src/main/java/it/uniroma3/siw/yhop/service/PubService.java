package it.uniroma3.siw.yhop.service;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.repository.PubRepository;

@Service
public class PubService {
@Autowired
 private PubRepository pubrepository;

	public List<Pub> findAll() {
		List<Pub> pubs=new ArrayList<>();
		for(Pub p: pubrepository.findAll()) {
			pubs.add(p);
		}
		return pubs;
	}
	public int countAll() {
		int contatore=0;
		for(Pub p: this.findAll()) {
			contatore++;
		}
		return contatore;
	}
	public boolean alreadyExists(Pub target) {
		return this.pubrepository.existsByNomeAndIndirizzo(target.getNome(), target.getIndirizzo());
	}
	@Transactional
	public void aggiungiPub(@Valid Pub pub) {
		pubrepository.save(pub);
		
	}
	public Pub findById(Long id) {
		return pubrepository.findById(id).get();
	}

}
