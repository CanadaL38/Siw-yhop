package it.uniroma3.siw.yhop.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.yhop.model.Birra;
import it.uniroma3.siw.yhop.model.Birrificio;
import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.model.Taplist;
import it.uniroma3.siw.yhop.service.BirraService;
import it.uniroma3.siw.yhop.service.BirrificioService;
import it.uniroma3.siw.yhop.service.PubService;
import it.uniroma3.siw.yhop.validator.BirraValidator;

@Controller
public class BirraController {
	@Autowired
	private BirrificioService breweryservice;
	@Autowired
	private PubService pubservice;
	@Autowired
	private BirraValidator beervalidator;
	@Autowired
	private BirraService beerservice;

	@GetMapping("/admin/addbeer/{birrificio_id}")
	public String addBirre(@PathVariable Long birrificio_id, Model model) {
	    Birra nuovaBirra = new Birra();
	    model.addAttribute("birra", nuovaBirra);
	    model.addAttribute("birrificio_id", birrificio_id);
	    return "/admin/Beer/BeerForm";
	}
	@PostMapping("/admin/addbeer/{birrificio_id}")
	public String addBeer(@PathVariable("birrificio_id") Long id, @Valid @ModelAttribute("birra") Birra birra, BindingResult bindingResults, Model model) {
		birra.setBirrificio(breweryservice.findById(id));
		beervalidator.validate(birra, bindingResults);
	    if (!bindingResults.hasErrors()) {
	        this.beerservice.aggiungiBirra(birra);
	        model.addAttribute("birra", birra);
	        model.addAttribute("pubs", this.pubservice.findAll());
    		model.addAttribute("birre", this.beerservice.findAll());
    		model.addAttribute("birrifici", this.breweryservice.findAll());
    		return "redirect:/default";
	    }
	    return "/admin/Beer/BeerForm.html";
	}


	
}
