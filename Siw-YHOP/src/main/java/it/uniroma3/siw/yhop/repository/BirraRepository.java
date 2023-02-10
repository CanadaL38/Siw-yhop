package it.uniroma3.siw.yhop.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.yhop.model.Birra;
import it.uniroma3.siw.yhop.model.Birrificio;

public interface BirraRepository extends CrudRepository<Birra,Long>{

	boolean existsByNomeAndBirrificio(String nome, Birrificio birrificio);
	

}
