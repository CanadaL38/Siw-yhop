package it.uniroma3.siw.yhop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.yhop.model.Birra;
import it.uniroma3.siw.yhop.model.Birrificio;
import it.uniroma3.siw.yhop.service.BirrificioService;

@Component
public class BirrificioValidator implements Validator {
	@Autowired
	private BirrificioService birrificioservice;
	@Override
	public boolean supports(Class<?> clazz) {
		return Birrificio.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.birrificioservice.alreadyExists((Birrificio)target)) {
			errors.reject("birrificio.duplicato");
	}

}
}
