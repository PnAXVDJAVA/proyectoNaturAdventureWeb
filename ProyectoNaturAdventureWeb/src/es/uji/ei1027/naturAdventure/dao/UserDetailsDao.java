package es.uji.ei1027.naturAdventure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.naturAdventure.domain.UserDetails;

@Repository
public class UserDetailsDao {

	private JdbcTemplate jdbcTemplate;
	
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
	
	public void addUser( UserDetails user ) {
		this.jdbcTemplate.update( "INSERT INTO User_details (userid, password, role) VALUES(?, ?, ?)", user.getUsername(), user.getPassword(), user.getRole() );
	}
	
}
