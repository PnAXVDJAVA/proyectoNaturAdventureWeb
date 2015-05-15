package es.uji.ei1027.naturAdventure.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.naturAdventure.domain.Customer;

public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Customer.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		Customer customer = (Customer) obj;
		
		if (customer.getNif().trim().equals(""))
			errors.rejectValue("customer.nif", "Campo vacío", "Introduce tu NIF");
		else if (customer.getNif().length() < 9 || customer.getNif().length() > 9)
			errors.rejectValue("customer.nif", "Campo vacío", "Introduce correctamente el NIF");
		if (customer.getName().trim().equals(""))
			errors.rejectValue("customer.name", "Campo vacío", "Introduce tu nombre");
		if (customer.getFirstSurname().trim().equals(""))
			errors.rejectValue("customer.firstSurname", "Campo vacío", "Introduce tu apellido");
		if ((String.valueOf(customer.getTelephone()).length() > 9 || String.valueOf(customer.getTelephone()).length() < 9))
			errors.rejectValue("customer.telephone", "Incorrecto", "Introduce un número de telefono correcto");
		if (customer.getEmail().trim().equals(""))
			errors.rejectValue("customer.email", "Falta email", "Introduce un email");
	}
}
