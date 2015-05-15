package es.uji.ei1027.naturAdventure.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.naturAdventure.dao.CustomerDao;
import es.uji.ei1027.naturAdventure.domain.Profile;
import es.uji.ei1027.naturAdventure.domain.UserDetails;

public class UserDetailsValidator implements Validator {

	private CustomerDao customerDao;
	
	@Autowired
	public void setCustomerDao( CustomerDao daoCustomer ) {
		this.customerDao = daoCustomer;
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
			System.out.println(username);
			Profile profil = customerDao.getProfileByUsername(username);
		}
		if (user.getPassword().trim().equals(""))
			error.rejectValue("userDetails.password", "Falta contraseña", "Introduce una contraseña");
	}

}
