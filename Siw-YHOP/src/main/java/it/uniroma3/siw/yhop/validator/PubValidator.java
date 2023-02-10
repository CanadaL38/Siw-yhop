package it.uniroma3.siw.yhop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



import it.uniroma3.siw.yhop.model.Pub;
import it.uniroma3.siw.yhop.service.PubService;

@Component
public class PubValidator implements Validator {
	@Autowired
	private PubService pubService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Pub.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.pubService.alreadyExists((Pub)target)) {
			errors.reject("pub.duplicato");
		}
	}

}
