package es.uji.ei1027.naturAdventure.service;

import org.springframework.beans.factory.annotation.Autowired;

import es.uji.ei1027.naturAdventure.dao.CustomerDao;
import es.uji.ei1027.naturAdventure.dao.InstructorDao;
import es.uji.ei1027.naturAdventure.dao.LoginDao;
import es.uji.ei1027.naturAdventure.domain.Roles;

public class LoginDaoFactory {
	
	private InstructorDao instructorDao;
	private CustomerDao customerDao;
	
	@Autowired
	public void setInstructorDao( InstructorDao instructorDao ) {
		this.instructorDao = instructorDao;
	}
	
	@Autowired
	public void setCustomerDao( CustomerDao customerDao ) {
		this.customerDao = customerDao;
	}
	
	public LoginDao getLoginDaoByUserRole( int role ) {
		if( role == Roles.INSTRUCTOR.getLevel() ) {
			return instructorDao;
		}
		return customerDao;
	}

}
