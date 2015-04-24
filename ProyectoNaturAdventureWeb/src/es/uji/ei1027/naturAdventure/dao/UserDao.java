package es.uji.ei1027.naturAdventure.dao;

import java.util.Collection;

import es.uji.ei1027.naturAdventure.domain.UserDetails;

public interface UserDao {
	
	UserDetails loadByUsername( String username, String password );
	Collection<UserDetails> listAllUsers();

}
