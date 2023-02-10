package it.uniroma3.siw.yhop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.model.Taplist;

public interface TaplistRepository extends CrudRepository<Taplist,Long>{

	boolean existsByNomeAndDescrizione(String nome, String descrizione);

	void save(Optional<Taplist> findById);

	Taplist findByPub(Pub pub);

}
