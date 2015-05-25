package es.uji.ei1027.naturAdventure.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.naturAdventure.dao.ActivityDao;
import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Booking;
import es.uji.ei1027.naturAdventure.domain.StartHour;

public class BookingValidator implements Validator {
	
	private ActivityDao activityDao;
	
	@Autowired
	public void setActivityDao( ActivityDao activityDao ) {
		this.activityDao = activityDao;
	}
	
	@Override
	public boolean supports(Class<?> cls) {
		return Activity.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Booking booking = (Booking) obj;
		
		int codActivity = booking.getCodActivity();
		Activity activity = this.activityDao.getActivity( codActivity );
		
		if( ! ( ( activity.getMinPartakers() <= booking.getNumPartakers() ) && ( booking.getNumPartakers() <= activity.getMaxPartakers() ) ) ) {
			errors.rejectValue("numPartakers", "Num. part. invalido", "Número de participantes inválido");
		}
				
		if( booking.getProposalPerformingDateString() == "" ) {
			errors.rejectValue("proposalPerformingDateString", "Fecha vacia", "Hay que introducir una fecha");
		}
		
		if( booking.getStartHour().equals( StartHour.Elige ) ) {
			errors.rejectValue("startHour", "Elegir hora", "Hay que elegir una hora");
		}
		
	}

}
