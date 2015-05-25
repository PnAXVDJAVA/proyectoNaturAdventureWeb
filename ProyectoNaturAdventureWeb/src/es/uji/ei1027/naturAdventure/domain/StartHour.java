package es.uji.ei1027.naturAdventure.domain;

import java.util.ArrayList;
import java.util.List;

public enum StartHour {
	
	Elige,
	morning,  //08:00 - 12:00
	afternoon, //12:00 - 20:00
	night;  //20:00 - 24:00
	
	public static StartHour getOpcion(String hour) {
		return valueOf(hour);
	}
	
	public static String [] getStringValues() {
		List<String> list = new ArrayList<String>();
		for( StartHour hour: values() ) {
			list.add( hour.toString() );
		}
		return (String[]) list.toArray( new String[ list.size() ] );
	}
}
