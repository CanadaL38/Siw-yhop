package it.uniroma3.siw.yhop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.yhop.model.Birra;
import it.uniroma3.siw.yhop.model.Birrificio;
import it.uniroma3.siw.yhop.service.BirraService;
import it.uniroma3.siw.yhop.service.BirrificioService;
import it.uniroma3.siw.yhop.service.PubService;
import it.uniroma3.siw.yhop.validator.BirrificioValidator;

@Controller
public class BirrificioController {
	@Autowired
	private BirrificioValidator birrificiovalidator;
	@Autowired
	private BirrificioService birrificioservice;
	@Autowired
	private PubService pubservice;
	@Autowired
	private BirraService birraservice;
	@GetMapping("/admin/addBirrificio")
	public String addBirrificio(Model model) {
		Birrificio nuovoBirrificio=new Birrificio();
		model.addAttribute("birrificio", nuovoBirrificio);
		return "admin/Birrificio/BirrificioForm";
	}
	@PostMapping("/admin/addBirrificio")
	public String addBrewery(@Valid @ModelAttribute("birrificio") Birrificio birrificio, BindingResult bindingResults, Model model) {
		birrificiovalidator.validate(birrificio, bindingResults);
		if (!bindingResults.hasErrors()) {
			birrificioservice.addBirrificio(birrificio);
			model.addAttribute("birrificio", birrificio);
			model.addAttribute("pubs", this.pubservice.findAll());
    		model.addAttribute("birre", this.birraservice.findAll());
    		model.addAttribute("birrifici", this.birrificioservice.findAll());
			return "redirect:/default";
		}
		return "admin/Birrificio/BirrificioForm.html";
	}
}
