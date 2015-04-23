package es.uji.ei1027.naturAdventure.domain;

import java.util.ArrayList;
import java.util.List;

public enum BookingStatus {
	
	pending,
	accepted,
	denied;
	
	public static BookingStatus getOpcion( String opcion ) {
		return valueOf( opcion );
	}
	
	public static String [] getStringValues() {
		List<String> list = new ArrayList<String>();
		for( BookingStatus status: values() ) {
			list.add( status.toString() );
		}
		return (String[]) list.toArray( new String[ list.size() ] );
	}

}
