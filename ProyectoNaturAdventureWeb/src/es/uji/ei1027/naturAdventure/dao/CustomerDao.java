package es.uji.ei1027.naturAdventure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.naturAdventure.domain.Customer;
import es.uji.ei1027.naturAdventure.domain.Profile;

@Repository
public class CustomerDao implements LoginDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	private static final class CustomerMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setNif( rs.getString( "nif" ) );
			customer.setName( rs.getString( "name" ) );
			customer.setFirstSurname( rs.getString( "firstSurname" ) );
			customer.setSecondSurname( rs.getString( "secondSurname" ) );
			customer.setEmail( rs.getString( "email" ) );
			customer.setTelephone( rs.getInt( "telephone" ) );
			customer.setUsername( rs.getString( "userid" ) );
			return customer;
		}
	}
	
	public List<Customer> getCustomers() {
		return this.jdbcTemplate.query( "SELECT * FROM Customer" , new CustomerMapper() );
	}
	
	public Customer getCustomer( String nif ) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM Customer WHERE nif = ?" , new Object[] {nif}, new CustomerMapper() );
	}
	
	public void addCustomer( Customer customer ) {
		this.jdbcTemplate.update( "INSERT INTO Customer ( nif, name, firstSurname, secondSurname, email, telephone, userid ) "
								+ "VALUES ( ?, ?, ?, ?, ?, ?, ? )", customer.getNif(), customer.getName(), customer.getFirstSurname(),
								customer.getSecondSurname(), customer.getEmail(), customer.getTelephone(), customer.getUsername() );
	}
	
	public void updateCustomer( Customer customer ) {
		this.jdbcTemplate.update( "UPDATE Customer SET name = ?, firstSurname = ?, secondSurname = ?, email = ?, telephone = ?, userid = ? "
								+ "WHERE nif = ?", customer.getName(), customer.getFirstSurname(), customer.getSecondSurname(), customer.getEmail(),
								customer.getTelephone(), customer.getUsername(), customer.getNif() );
	}
	
	public void deleteCustomer( Customer customer ) {
		this.jdbcTemplate.update( "DELETE FROM Customer WHERE nif = ?", customer.getNif() );
	}

	@Override
	public Profile getProfileByUsername(String username) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM Customer WHERE userid = ?", new Object[] {username} , new CustomerMapper() );
	}

}
