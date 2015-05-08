package es.uji.ei1027.naturAdventure.domain;

import java.util.ArrayList;
import java.util.List;

public enum Level {

	Elige,
	easy,
	medium,
	hard;

	public static Level getOpcion(String level) {
		return valueOf(level);
	}
	
	public static String [] getStringValues() {
		List<String> list = new ArrayList<String>();
		for( Level level: values() ) {
			list.add( level.toString() );
		}
		return (String[]) list.toArray( new String[ list.size() ] );
	}
}
