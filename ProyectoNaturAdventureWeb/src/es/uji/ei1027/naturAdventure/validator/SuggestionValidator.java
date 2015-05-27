package es.uji.ei1027.naturAdventure.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.naturAdventure.domain.MessageType;
import es.uji.ei1027.naturAdventure.domain.Suggestion;
import es.uji.ei1027.naturAdventure.service.RegularExpression;

public class SuggestionValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Suggestion.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		Suggestion suggestion = ( Suggestion ) obj;
		
		if( suggestion.getName().trim().equals( "" ) ) {
			errors.rejectValue("name", "Obligatorio", "Introduce un nombre");
		}
		
		if( !RegularExpression.matches( suggestion.getEmail() , RegularExpression.EMAIL_RE ) ) {
			errors.rejectValue("email", "Email incorrecto", "Introduce un email correcto");
		}
		
		if( suggestion.getMessageType().equals( MessageType.Elige ) ) {
			errors.rejectValue("messageType", "Obligatorio", "Elige una opción");
		}
		
		if( suggestion.getMessage().trim().equals( "" ) ) {
			errors.rejectValue("message", "Obligatorio", "Introduce un texto");
		}
		
	}

}
