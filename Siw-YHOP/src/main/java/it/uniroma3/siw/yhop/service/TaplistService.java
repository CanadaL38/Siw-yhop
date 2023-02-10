package it.uniroma3.siw.yhop.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.model.Taplist;
import it.uniroma3.siw.yhop.repository.TaplistRepository;

@Service
public class TaplistService {
	@Autowired
	private TaplistRepository taplistrepository;

	public boolean alreadyExists(Taplist target) {
		// TODO Auto-generated method stub
	 return this.taplistrepository.existsByNomeAndDescrizione(target.getNome(),target.getDescrizione());
	}

	public void aggiungiTaplist(@Valid Taplist taplist) {
		// TODO Auto-generated method stub
		this.taplistrepository.save(taplist);
	}

	public Taplist findById(Long id) {
		return this.taplistrepository.findById(id).orElse(null);                       // ho dovuto aggiungere questo orElse perchè mi dava errore per convertire Optional<Taplist> to Taplist
	}

	public void save(@Valid Taplist taplist) {
		// TODO Auto-generated method stub
		this.taplistrepository.save(taplist);
		
	}

	public void editTaplist(Taplist nuovaTaplist) {
		// TODO Auto-generated method stub
		this.taplistrepository.save(nuovaTaplist);
		
	}

	public Taplist fidByPub(Pub pub) {
		return taplistrepository.findByPub(pub);
	}

}
