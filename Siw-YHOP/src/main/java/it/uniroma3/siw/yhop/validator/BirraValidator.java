package it.uniroma3.siw.yhop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.yhop.model.Birra;
import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.service.BirraService;

@Component
public class BirraValidator implements Validator {
	@Autowired
	private BirraService birraservice;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Birra.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.birraservice.alreadyExists((Birra)target)) {
			errors.reject("birra.duplicato");
		}
		
	}

}
