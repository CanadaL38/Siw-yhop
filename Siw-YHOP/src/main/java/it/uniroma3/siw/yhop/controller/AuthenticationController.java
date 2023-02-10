package it.uniroma3.siw.yhop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.yhop.service.BirraService;
import it.uniroma3.siw.yhop.service.BirrificioService;
import it.uniroma3.siw.yhop.service.CredentialsService;
import it.uniroma3.siw.yhop.service.PubService;
import it.uniroma3.siw.yhop.model.Credentials;

@Controller
public class AuthenticationController {
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private PubService pubsService;
	@Autowired
	private BirraService beersService;
	@Autowired
	private BirrificioService breweriesService;
	
	@GetMapping("/login")
	public String getAdminLogin(Model model) {
		return "/adminLogin.html";
	}
	@GetMapping("/logout") 
	public String logout(Model model) {
		return "index";
	}
	
   @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    		int numberOfPubs=pubsService.countAll();
    		int numberOfBeers=beersService.countAll();
    		int numberOfBreweries=breweriesService.countAll();
    		model.addAttribute("numberOfPubs", numberOfPubs);
    		model.addAttribute("numberOfBeers", numberOfBeers);
    		model.addAttribute("numberOfBreweries", numberOfBreweries);
    		model.addAttribute("pubs", this.pubsService.findAll());
    		model.addAttribute("birre", this.beersService.findAll());
    		model.addAttribute("birrifici", this.breweriesService.findAll());
            return "/admin/adminIndex.html";
        }
        return "index";
    }
}
