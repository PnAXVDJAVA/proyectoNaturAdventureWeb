package es.uji.ei1027.naturAdventure.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.naturAdventure.domain.UserDetails;

public class PasswordValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		return UserDetails.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserDetails user = (UserDetails) obj;
		if( user.getPassword().trim().equals("") ) {
			errors.rejectValue( "password" , "obligatorio", "Hay que introducir la contraseña" );
		}
	}

}
