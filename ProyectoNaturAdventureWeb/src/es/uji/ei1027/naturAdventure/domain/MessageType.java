package es.uji.ei1027.naturAdventure.domain;

import java.util.ArrayList;
import java.util.List;

public enum MessageType {
	
	Elige,
	Sugerencia,
	Queja,
	Duda,
	Otros;
	
	public static MessageType getOpcion(String type) {
		return valueOf(type);
	}
	
	public static String [] getStringValues() {
		List<String> list = new ArrayList<String>();
		for( MessageType type: values() ) {
			list.add( type.toString() );
		}
		return (String[]) list.toArray( new String[ list.size() ] );
	}

}
