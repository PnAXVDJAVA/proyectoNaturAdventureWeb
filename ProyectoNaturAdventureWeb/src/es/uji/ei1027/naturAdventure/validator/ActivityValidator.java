package es.uji.ei1027.naturAdventure.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Level;

public class ActivityValidator implements Validator{

	@Override
	public boolean supports(Class<?> cls) {
		return Activity.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Activity activity = (Activity) obj;
		
		if (activity.getName().trim().equals(""))
			errors.rejectValue("name", "Obligatorio", "Introduce un nombre");
		if (activity.getDescription().trim().equals(""))
			errors.rejectValue("description", "Obligatorio", "Introduce una descripción");
		if (activity.getPricePerPerson() < 0.0) 
			errors.rejectValue("pricePerPerson", "Mayor que 0", "Introduce un precio correcto");
		if (activity.getDuration() < 0)
			errors.rejectValue("duration", "Mayor que 0", "Introduce una duración correcta");
		if (activity.getMaxPartakers() < 0) 
			errors.rejectValue("maxPartakers", "Mayor que 0", "Introduce una cantidad correcta");
		if (activity.getMinPartakers() < 0) 
			errors.rejectValue("minPartakers", "Mayor que 0", "Introduce una cantidad correcta");
		if (activity.getMinPartakers() > activity.getMaxPartakers()) 
			errors.rejectValue("minPartakers", "Max >= Min", "El mínimo de participantes tiene que ser menor o igual que el máximo de participantes");
		if (activity.getLevel().equals(Level.Elige))
			errors.rejectValue("level", "Elige Nivel", "Elige un nivel de dificultad");
	}
}
