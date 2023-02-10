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
import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.model.Taplist;
import it.uniroma3.siw.yhop.service.BirraService;
import it.uniroma3.siw.yhop.service.BirrificioService;
import it.uniroma3.siw.yhop.service.PubService;
import it.uniroma3.siw.yhop.service.TaplistService;
import it.uniroma3.siw.yhop.validator.TaplistValidator;

@Controller
public class TaplistController {
	@Autowired
	private TaplistService taplistservice;

	@Autowired
	private BirraService birraservice;
	
	@Autowired
	private PubService pubservice;
	
	@Autowired
	private BirrificioService birrificioservice;
	
	@Autowired
	private TaplistValidator tv;
	
	@GetMapping("/admin/addtaplist/{pub_id}")
	public String createTaplistForm(@PathVariable Long pub_id, Model model) {
		Taplist nuvaTaplist = new Taplist();
		model.addAttribute("taplist", nuvaTaplist);
		model.addAttribute("pub_id", pub_id);
		model.addAttribute("birre", this.birraservice.findAll());
		return "/admin/Taplist/TaplistForm.html";
	}
	@PostMapping("/admin/addtaplist/{pub_id}")
	public String addTaplist(@PathVariable("pub_id") Long id, @Valid @ModelAttribute("taplist") Taplist taplist,
			BindingResult bindingResults, Model model) {
		Pub pub = pubservice.findById(id);
		pub.setTaplist(taplist);
		taplist.setPub(pub);
		this.tv.validate(taplist, bindingResults);
		if (!bindingResults.hasErrors()) {
			taplistservice.save(taplist);
			model.addAttribute("taplist", this.taplistservice.fidByPub(pub));
			model.addAttribute("pub_id", id);
			model.addAttribute("pubs", this.pubservice.findAll());
    		model.addAttribute("birre", this.birraservice.findAll());
    		model.addAttribute("birrifici", this.birrificioservice.findAll());
    		return "redirect:/default";
		} else {

			return "/admin/Taplist/TaplistForm.html";
		}
	}
	@GetMapping("/admin/edittaplist/{pub_id}")
	public String getEditChefForm(@PathVariable("pub_id") Long pub_id, Model model) {
		Taplist taplist = pubservice.findById(pub_id).getTaplist();
		model.addAttribute("taplist", taplist);
		model.addAttribute("birre", birraservice.findAll());
		return "/admin/Taplist/EditTaplist.html";
	}
	@PostMapping("/admin/edittaplist/{pub_id}")
	public String editTaplist(@PathVariable("pub_id") Long id,  @Valid @ModelAttribute("taplist") Taplist taplist,
			BindingResult bindingResults, Model model) {	 
		if (!bindingResults.hasErrors()) {
			Taplist nuovaTaplist = this.pubservice.findById(id).getTaplist();
			nuovaTaplist.setNome(taplist.getNome());
			nuovaTaplist.setDescrizione(taplist.getDescrizione());
			nuovaTaplist.setPub(this.pubservice.findById(id));
			nuovaTaplist.setBirre(taplist.getBirre());
			this.taplistservice.editTaplist(nuovaTaplist);
			model.addAttribute("nuovaTaplist", nuovaTaplist);
			model.addAttribute("pubs", this.pubservice.findAll());
    		model.addAttribute("birre", this.birraservice.findAll());
    		model.addAttribute("birrifici", this.birrificioservice.findAll());
			return "/admin/adminIndex";
		}
		else
			return "/admin/Taplist/EditTaplist.html";
	}
}

