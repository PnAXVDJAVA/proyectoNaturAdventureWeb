package es.uji.ei1027.naturAdventure.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.naturAdventure.dao.CustomerDao;
import es.uji.ei1027.naturAdventure.domain.Customer;
import es.uji.ei1027.naturAdventure.service.RegularExpression;

public class CustomerValidator implements Validator {
	
	private CustomerDao customerDao;
	
	@Autowired
	public void setCustomerDao( CustomerDao customerDao ) {
		this.customerDao = customerDao;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return Customer.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		Customer customer = (Customer) obj;
		
		try {
			this.customerDao.getCustomer( customer.getNif() );
			errors.rejectValue("customer.nif", "NIF registrado", "NIF ya registrado en el sistema");
		}
		catch( EmptyResultDataAccessException e ) {
			//
		}
		
		if ( !RegularExpression.matches( customer.getNif() , RegularExpression.NIF_RE ) )
			errors.rejectValue("customer.nif", "Campo vacío", "Introduce correctamente el NIF");
		
		if (customer.getName().trim().equals(""))
			errors.rejectValue("customer.name", "Campo vacío", "Introduce tu nombre");
		
		if (customer.getFirstSurname().trim().equals(""))
			errors.rejectValue("customer.firstSurname", "Campo vacío", "Introduce tu apellido");
				
		if( !RegularExpression.matches( customer.getTelephone(), RegularExpression.TELEPHONE_RE ) ) {
			errors.rejectValue("customer.telephone", "Incorrecto", "Introduce un número de telefono correcto");
		}
		
		if ( !RegularExpression.matches( customer.getEmail() , RegularExpression.EMAIL_RE ) )
			errors.rejectValue("customer.email", "Falta email", "Introduce una dirección de correo correcta");
	}
}
