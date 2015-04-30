package es.uji.ei1027.naturAdventure.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CodeGetter {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	public CodeGetter() {
		super();
	}
	
	public int getNextCode( String fieldName, String tableName ) {
		int nextCode;
		
		try {
			nextCode = this.jdbcTemplate.queryForObject( "SELECT MAX(" + fieldName + ") FROM " + tableName , Integer.class );
		}
		catch( NullPointerException e ) {
			nextCode = 0;
		}
		
		nextCode++;
		return nextCode;
	}

}
