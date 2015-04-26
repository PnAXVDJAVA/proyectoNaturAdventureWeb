package es.uji.ei1027.naturAdventure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.naturAdventure.domain.UserDetails;

@Repository
public class UserDetailsDao {

	private JdbcTemplate jdbcTemplate;
	private BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	
	@Autowired
	public void setDataSource( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	private static final class UserDetailsMapper implements RowMapper<UserDetails> {

		@Override
		public UserDetails mapRow(ResultSet rs, int rowInt) throws SQLException {
			UserDetails userDetails = new UserDetails();
			userDetails.setUsername( rs.getString( "userID" ) );
			userDetails.setPassword( rs.getString( "password" ) );
			userDetails.setRole( rs.getInt( "role" ) );
			return userDetails;
		}
	}
	
	public List<UserDetails> getUsers() {
		return this.jdbcTemplate.query( "SELECT * FROM User_details" , new UserDetailsMapper() );
	}
	
	public UserDetails getUser( String username ) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM User_details WHERE userID = ?", new Object[] { username },  new UserDetailsMapper() );
	}
	
	public void addUser( UserDetails user ) {
		user.setPassword( passwordEncryptor.encryptPassword( user.getPassword() ) );
		this.jdbcTemplate.update( "INSERT INTO User_details (userid, password, role) VALUES(?, ?, ?)", user.getUsername(), user.getPassword(), user.getRole() );
	}
	
	public void addUser( UserDetails user, int role ) {
		user.setPassword( passwordEncryptor.encryptPassword( user.getPassword() ) );
		user.setRole( role );
		this.jdbcTemplate.update( "INSERT INTO User_details (userid, password, role) VALUES(?, ?, ?)", user.getUsername(), user.getPassword(), user.getRole() );
	}
	
	public void updateUser( UserDetails user ) {
		user.setPassword( passwordEncryptor.encryptPassword( user.getPassword() ) );
		this.jdbcTemplate.update( "UPDATE User_details SET password = ?, role = ? WHERE userid = ?", user.getPassword(), user.getRole(), user.getUsername() );
	}
	
	public void updateUser( UserDetails user, int role ) {
		user.setPassword( passwordEncryptor.encryptPassword( user.getPassword() ) );
		user.setRole( role );
		this.jdbcTemplate.update( "UPDATE User_details SET password = ?, role = ? WHERE userid = ?", user.getPassword(), user.getRole(), user.getUsername() );
	}
	
}
