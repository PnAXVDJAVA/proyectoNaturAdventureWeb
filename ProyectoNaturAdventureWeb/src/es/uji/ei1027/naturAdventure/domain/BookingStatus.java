package es.uji.ei1027.naturAdventure.domain;

public enum BookingStatus {
	
	pending,
	accepted,
	denied;
	
	public static BookingStatus getOpcion( String opcion ) {
		return valueOf( opcion );
	}

}
