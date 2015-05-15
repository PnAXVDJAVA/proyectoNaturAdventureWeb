package es.uji.ei1027.naturAdventure.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.naturAdventure.dao.UserDetailsDao;
import es.uji.ei1027.naturAdventure.domain.UserDetails;

public class UserDetailsValidator implements Validator {

	private UserDetailsDao userDetailsDao;
	
	@Autowired
	public void setUserDetailsDao( UserDetailsDao userDetailsDao ) {
		this.userDetailsDao = userDetailsDao;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return UserDetailsValidator.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors error) {
		
		UserDetails user = (UserDetails) obj;
		
		String username = user.getUsername();
		if (username.trim().equals(""))
			error.rejectValue("userDetails.username", "Falta username", "Introduce un username");
		else {
			try {				
				userDetailsDao.getUser( username );
				error.rejectValue("userDetails.username", "Nombre en uso", "El nombre de usuario ya existe");
			}
			catch( EmptyResultDataAccessException e ) {
				//No hacemos nada, no ha saltado la excepción que dice que no ha obtenido datos de la base de datos
				//por lo tanto el nombre de usuario no existe
			}
		}
		if (user.getPassword().trim().equals(""))
			error.rejectValue("userDetails.password", "Falta contraseña", "Introduce una contraseña");
	}

}
