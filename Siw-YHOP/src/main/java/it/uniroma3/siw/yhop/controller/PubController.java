package it.uniroma3.siw.yhop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.model.Taplist;
import it.uniroma3.siw.yhop.service.BirraService;
import it.uniroma3.siw.yhop.service.BirrificioService;
import it.uniroma3.siw.yhop.service.PubService;
import it.uniroma3.siw.yhop.service.TaplistService;
import it.uniroma3.siw.yhop.validator.PubValidator;
import it.uniroma3.siw.yhop.validator.TaplistValidator;

@Controller
public class PubController {
	@Autowired
	private PubService pubservice;
	@Autowired
	private BirrificioService birrificioservice;
	@Autowired
	private BirraService birraservice;
	@Autowired
	private TaplistService taplistservice;
	@Autowired
	private BirraService beerservice;
	@Autowired
	private PubValidator pubvalidator;
	@Autowired
	private TaplistValidator taplistvalidator;
	
	@GetMapping("/user/pubs")
	public String getPubs(Model model) {
		List<Pub> pubs = pubservice.findAll();
		model.addAttribute("pubs", pubs);
		return "/user/pubs.html";
	}
	@GetMapping("/user/pub/{pub_id}")
	public String getPub(@PathVariable Long pub_id, Model model) {
		Pub pub=pubservice.findById(pub_id);
		model.addAttribute("pub", pub);
		model.addAttribute("taplist", pub.getTaplist());
		return "/user/pub.html";
	}
	
	@GetMapping("/admin/addPub")
	public String addPubs(Model model) {
	    Pub nuovoPub = new Pub();
	    Taplist nuovaTaplist=new Taplist();
	    nuovoPub.setTaplist(nuovaTaplist);
	    nuovaTaplist.setPub(nuovoPub);
	    model.addAttribute("pub", nuovoPub);
	    model.addAttribute("taplist", nuovaTaplist);
	    model.addAttribute("birre", beerservice.findAll());
	    return "/admin/Pub/PubForm";
	}
	
	@PostMapping("/admin/addPub")
	public String addPub(@Valid @ModelAttribute("pub") Pub pub,
			BindingResult bindingResults, Model model) {
		pubvalidator.validate(pub, bindingResults);
		if (!bindingResults.hasErrors()) {			
			this.pubservice.aggiungiPub(pub);
			model.addAttribute("pubs", this.pubservice.findAll());
    		model.addAttribute("birre", this.birraservice.findAll());
    		model.addAttribute("birrifici", this.birrificioservice.findAll());
    		return "redirect:/default";

		}
		return "admin/Pub/PubForm.html";
	}
}
