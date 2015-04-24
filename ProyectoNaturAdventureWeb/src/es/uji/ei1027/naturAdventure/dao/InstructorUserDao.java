package es.uji.ei1027.naturAdventure.dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.domain.UserDetails;

@Repository
public class InstructorUserDao implements UserDao {
	
	private InstructorDao instructorDao;
	
	@Autowired
	public void setInstructorDao( InstructorDao instructorDao ) {
		this.instructorDao = instructorDao;
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
		
		/*BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		if( passwordEncryptor.checkPassword( password ,  foundUser.getPassword() ) ) {
			return foundUser;
		}*/
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
		List<Instructor> instructorsList = instructorDao.getInstructors();
		for( Instructor instructor: instructorsList ) {
			UserDetails user = new UserDetails();
			user.setUsername( instructor.getUserID() );
			user.setPassword( instructor.getPassword() );
			usersList.add( user );
		}
		
		return usersList;
	}

}
