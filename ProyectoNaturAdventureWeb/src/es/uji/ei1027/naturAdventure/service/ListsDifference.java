package es.uji.ei1027.naturAdventure.service;

import java.util.LinkedList;
import java.util.List;

public class ListsDifference {
	
	public static <T> List<T> listsDifference( List<T> list1, List<T> list2 ) {
		List<T> res = new LinkedList<T>();
		for( T item: list1 ) {
			if( !list2.contains( item ) ) {
				res.add( item );
			}
		}
		return res;
	}

}
