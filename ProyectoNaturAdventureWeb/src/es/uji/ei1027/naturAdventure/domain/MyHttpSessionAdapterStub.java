package es.uji.ei1027.naturAdventure.domain;

import java.util.HashMap;
import java.util.Map;

public class MyHttpSessionAdapterStub extends MyHttpSession {
	
	private Map<String, Object> myAttributeMap;
	
	public MyHttpSessionAdapterStub() {
		this.myAttributeMap = new HashMap<>();
	}
	
	@Override
	public Object getAttribute(String name) {
		return this.myAttributeMap.get( name );
	}
	
	@Override
	public void setAttribute(String name, Object obj) {
		this.myAttributeMap.put( name, obj);
	}

}
