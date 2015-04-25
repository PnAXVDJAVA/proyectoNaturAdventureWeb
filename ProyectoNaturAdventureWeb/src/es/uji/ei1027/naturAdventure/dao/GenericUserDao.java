package es.uji.ei1027.naturAdventure.dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

import es.uji.ei1027.naturAdventure.domain.UserDetails;

public class GenericUserDao implements UserDao {
	
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	public void setUserDetailsDao( UserDetailsDao udd ) {
		this.userDetailsDao = udd;
	}

	@Override
	public UserDetails loadByUsername(String username, String password) {
		boolean encontrado = false;
		UserDetails foundUser = null;
		Collection<UserDetails> users = listAllUsers();
		for( UserDetails user: users ) {
			if( user.getUsername().equals( username ) ) {
				encontrado = true;
				foundUser = user;
				break;
			}
		}
		
		if( !encontrado ) {
			return null;
		}
		
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		if( passwordEncryptor.checkPassword( password ,  foundUser.getPassword() ) ) {
			return foundUser;
		}
		if( foundUser.getPassword().equals( password ) ) {
			return foundUser;
		}
		else {
			return null;
		}
	}

	@Override
	public Collection<UserDetails> listAllUsers() {
		Collection<UserDetails> usersList = new LinkedList<>();
		List<UserDetails> usersListDao = userDetailsDao.getUsers();
		for( UserDetails user: usersListDao ) {
			usersList.add( user );
		}
		
		return usersList;
	}

}
