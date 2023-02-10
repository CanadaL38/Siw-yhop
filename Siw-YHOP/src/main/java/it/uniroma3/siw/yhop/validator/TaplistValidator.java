package it.uniroma3.siw.yhop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import it.uniroma3.siw.yhop.model.Taplist;
import it.uniroma3.siw.yhop.service.TaplistService;

@Component
public class TaplistValidator implements Validator{
	@Autowired
	private TaplistService taplistService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Taplist.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.taplistService.alreadyExists((Taplist)target)) {
			errors.reject("taplist.duplicato");
		}
	}

}
