package com.andersonmarques.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.andersonmarques.models.Produto;

public class ProdutoValidation implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		//Informa que pode chamar o validator para a classe produto
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//Se o título for vazio ele rejeita
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		Produto p = (Produto) target;
		
		if(p.getPaginas() == null || p.getPaginas() <= 0 ) {
			errors.rejectValue("paginas", "field.required");
		}
	}
}
