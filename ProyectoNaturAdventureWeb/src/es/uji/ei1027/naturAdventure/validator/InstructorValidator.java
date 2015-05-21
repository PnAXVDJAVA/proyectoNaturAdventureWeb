package es.uji.ei1027.naturAdventure.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.service.RegularExpression;

public class InstructorValidator implements Validator {

	private final int[] meses30dias = {4, 6, 9, 11};
	
	@Override
	public boolean supports(Class<?> cls) {
		return Instructor.class.equals(cls);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void validate(Object obj, Errors errors) {
		
		Instructor instructor = (Instructor) obj;
		
		if( !RegularExpression.matches( instructor.getNif() ,  RegularExpression.NIF_RE ) ) {
			errors.rejectValue("instructor.nif", "Campo vacío", "Introduce correctamente el NIF");
		}
		
		if (instructor.getName().trim().equals(""))
			errors.rejectValue("instructor.name", "Campo vacío", "Introduce tu nombre");
		
		if (instructor.getFirstSurname().trim().equals(""))
			errors.rejectValue("instructor.firstSurname", "Campo vacío", "Introduce tu apellido");
		
		if (instructor.getAddress().trim().equals(""))
			errors.rejectValue("instructor.address", "Campo vacío", "Introduce tu dirección");
		
		if ( !RegularExpression.matches( instructor.getTelephone() ,  RegularExpression.TELEPHONE_RE ) )
			errors.rejectValue("instructor.telephone", "Incorrecto", "Introduce un número de telefono correcto");
		
		//comprobamos que la fecha de nacimiento sea correcta
		
		Integer day = instructor.getDayOfBirth();
		Integer month = instructor.getMonthOfBirth();
		Integer year = instructor.getYearOfBirth();
		
		if( day == null ) {
			errors.rejectValue("instructor.dayOfBirth", "Positivo", "Introduce un día correcto");
		}
		else if (day <= 0) {
			errors.rejectValue("instructor.dayOfBirth", "Positivo", "Introduce un día correcto");
		}
		else if (day > 31) {
			errors.rejectValue("instructor.dayOfBirth", "Mes sol 31 dias", "El mes solo tiene 31 dias");
		}
		else if( month == null ) {
			errors.rejectValue("instructor.monthOfBirth", "Positivo", "Introduce un mes correcto");
		}
		else if (month <= 0) {
			errors.rejectValue("instructor.monthOfBirth", "Positivo", "Introduce un mes correcto");
		}
		else if (month > 12) {
			errors.rejectValue("instructor.monthOfBirth", "Solo 12 meses", "El año solo tiene 12 meses");
		}
		else if( ( month == 2 && day > 29 ) ) {
			errors.rejectValue("instructor.dayOfBirth", "Mes con 30 dias", "Día inválido para el mes seleccionado");
		}
			
		else if( day == 31 ) {
			for ( int i = 0; i < meses30dias.length; i++ ) {
				if ( meses30dias[i] == month ) {
					errors.rejectValue("instructor.dayOfBirth", "Mes con 30 dias", "Día inválido para el mes seleccionado");
				}
			}
		}
		else if( year == null ) {
			errors.rejectValue("instructor.yearOfBirth", "Positivo", "Introduce un año correcto");
		}

		else if (year < 1900) {
			errors.rejectValue("instructor.yearOfBirth", "Positivo", "Introduce un año correcto");
		}

		else if ( year > ( 1900 + new Date().getYear() ) ) {
			errors.rejectValue("instructor.yearOfBirth", "Maximo año posible", "Introduce un año correcto");
		}

		
		if ( !RegularExpression.matches( instructor.getEmail() ,  RegularExpression.EMAIL_RE ) )
			errors.rejectValue("instructor.email", "Falta email", "Introduce dirección de correo correcta");
		
		if (instructor.getBankAccount().length() < 24 || instructor.getBankAccount().length() > 24) 
			errors.rejectValue("instructor.bankAccount", "Cuenta incorrecta", "Introduce una cuenta bancaria correcta");
	}

}
