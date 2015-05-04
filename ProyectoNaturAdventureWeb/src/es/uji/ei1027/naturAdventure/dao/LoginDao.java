package es.uji.ei1027.naturAdventure.dao;

import es.uji.ei1027.naturAdventure.domain.Profile;

public interface LoginDao {
	
	public Profile getProfileByUsername( String username );

}
