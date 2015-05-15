package es.uji.ei1027.naturAdventure.validator;

import java.util.Date;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.uji.ei1027.naturAdventure.domain.Instructor;

public class InstructorValidator implements Validator {

	private final int[] meses31dias = {4, 6, 9, 11};
	
	@Override
	public boolean supports(Class<?> cls) {
		return Instructor.class.equals(cls);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void validate(Object obj, Errors errors) {
		
		Instructor instructor = (Instructor) obj;
		
		if (instructor.getNif().trim().equals(""))
			errors.rejectValue("instructor.nif", "Campo vacío", "Introduce tu NIF");
		if (instructor.getName().trim().equals(""))
			errors.rejectValue("instructor.name", "Campo vacío", "Introduce tu nombre");
		if (instructor.getFirstSurname().trim().equals(""))
			errors.rejectValue("instructor.firstSurname", "Campo vacío", "Introduce tu apellido");
		if (instructor.getAddress().trim().equals(""))
			errors.rejectValue("instructor.address", "Campo vacío", "Introduce tu dirección");
		if ((String.valueOf(instructor.getTelephone()).length() > 9 || String.valueOf(instructor.getTelephone()).length() < 9))
			errors.rejectValue("instructor.telephone", "Incorrecto", "Introduce un número de telefono correcto");
		
		//comprobamos que la fecha de nacimiento sea correcta
		int day = instructor.getDayOfBirth();
		int month = instructor.getMonthOfBirth();
		int year = instructor.getYearOfBirth();
		
		if (day <= 0)
			errors.rejectValue("instructor.dayOfBirth", "Positivo", "Introduce un día correcto");
		if (month <= 0)
			errors.rejectValue("instructor.monthOfBirth", "Positivo", "Introduce un mes correcto");
		if (year < 1900)
			errors.rejectValue("instructor.yearOfBirth", "Positivo", "Introduce un año correcto");
		if (day > 31)
			errors.rejectValue("instructor.dayOfBirth", "Mes sol 31 dias", "El mes solo tiene 31 dias");
		if (month > 12)
			errors.rejectValue("instructor.monthOfBirth", "Solo 12 meses", "El año solo tiene 12 meses");
		if (year > (1900 + new Date().getYear())) 
			errors.rejectValue("instructor.yearOfBirth", "Maximo año posible", "Introduce un año correcto (inferior al actual)");
		for (int i = 0; i < meses31dias.length; i++) {
			if (meses31dias[i] == month) {
				if (day == 31)
					errors.rejectValue("instructor.dayOfBirth", "Mes con 30 dias", "El mes solo tiene 30 dias");
			}
		}
		if (instructor.getEmail().trim().equals(""))
			errors.rejectValue("instructor.email", "Falta email", "Introduce un email");
		if (instructor.getBankAccount().length() < 24 || instructor.getBankAccount().length() > 24) 
			errors.rejectValue("instructor.bankAccount", "Cuenta incorrecta", "Introduce una cuenta bancaria correcta");
	}

}
