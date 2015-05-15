package es.uji.ei1027.naturAdventure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.domain.Profile;
import es.uji.ei1027.naturAdventure.service.DateService;

@Repository
public class InstructorDao implements LoginDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	private static final class InstructorMapper implements RowMapper<Instructor> {
		public Instructor mapRow( ResultSet rs, int rowNum ) throws SQLException {
			Instructor instructor = new Instructor();
			instructor.setNif( rs.getString( "nif" ) );
			instructor.setName( rs.getString( "name" ) );
			instructor.setFirstSurname( rs.getString( "firstSurname" ) );
			instructor.setSecondSurname( rs.getString( "secondSurname" ) );
			instructor.setAddress( rs.getString( "address" ) );
			instructor.setTelephone( rs.getInt( "telephone" ) );
			instructor.setDateOfBirth( rs.getDate( "dateofbirth" ) );
			instructor.setEmail( rs.getString( "email" ) );
			instructor.setBankAccount( rs.getString( "bankaccount" ) );
			instructor.setUserID( rs.getString( "userid" ) );
			//parseamos la fecha para obtener los tres valores independientes
			DateService dateService = new DateService( instructor.getDateOfBirth() );
			instructor.setDayOfBirth( dateService.getDay() );
			instructor.setMonthOfBirth( dateService.getMonth() );
			instructor.setYearOfBirth( dateService.getYear() );
			return instructor;
		}
	}
	
	public List<Instructor> getInstructors() {
		return this.jdbcTemplate.query( "SELECT * FROM Instructor" , new InstructorMapper() );
	}
	
	public Instructor getInstructor( String nif ) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM Instructor WHERE nif = ?", new Object [] { nif } , new InstructorMapper() );
	}
	
	public void addInstructor( Instructor instructor ) {
		DateService dateService = new DateService( instructor.getDayOfBirth() , instructor.getMonthOfBirth(), 
													instructor.getYearOfBirth() );
		instructor.setDateOfBirth( dateService.getDate() );
		this.jdbcTemplate.update( "INSERT INTO Instructor ( nif, name, firstsurname, secondsurname, address, telephone, dateofbirth, email, bankaccount, userid ) "
								+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )", instructor.getNif(), instructor.getName(), instructor.getFirstSurname(), 
								instructor.getSecondSurname(), instructor.getAddress(), (int) instructor.getTelephone(), instructor.getDateOfBirth(), instructor.getEmail(), 
								instructor.getBankAccount(), instructor.getUserID() );
	}
	
	public void updateInstructor( Instructor instructor ) {
		DateService dateService = new DateService( instructor.getDayOfBirth() , instructor.getMonthOfBirth(), 
				instructor.getYearOfBirth() );
		instructor.setDateOfBirth( dateService.getDate() );
		this.jdbcTemplate.update( "UPDATE Instructor SET name = ?, firstsurname = ?, secondsurname = ?, address = ?, telephone = ?, dateofbirth = ?, email = ?, bankaccount = ? WHERE nif = ?",
									instructor.getName(), instructor.getFirstSurname(), instructor.getSecondSurname(), instructor.getAddress(), (int) instructor.getTelephone(), instructor.getDateOfBirth(), 
									instructor.getEmail(), instructor.getBankAccount(), instructor.getNif() );
	}
	
	public void deleteInstructor( Instructor instructor ) {
		this.jdbcTemplate.update( "DELETE FROM User_details WHERE userid = ?", instructor.getUserID() );
		this.jdbcTemplate.update( "DELETE FROM Instructor WHERE nif = ?", instructor.getNif() );
	}
	
	public List<Instructor> getSpecializedInstructors( int codActivity ) {
		return this.jdbcTemplate.query( "SELECT i.* FROM Instructor AS i JOIN Specialized AS s ON( i.nif = s.instructorNif ) "
									+ "WHERE s.codActivity = ?", new Object[] {codActivity}, new InstructorMapper() );
	}
	
	public List<Instructor> getAssignedInstructors( int codBooking ) {
		return this.jdbcTemplate.query( "SELECT i.* FROM Instructor AS i JOIN Booking_Assigns AS ba ON( i.nif = ba.instructorNif ) "
										+ "WHERE ba.codBooking = ?", new Object[] {codBooking}, new InstructorMapper() );
	}

	@Override
	public Profile getProfileByUsername( String username ) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM Instructor WHERE userid = ?", new Object [] { username } , new InstructorMapper() );
	}

}
