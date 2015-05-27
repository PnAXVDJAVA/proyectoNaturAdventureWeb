package es.uji.ei1027.naturAdventure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.naturAdventure.domain.MessageType;
import es.uji.ei1027.naturAdventure.domain.Suggestion;

@Repository
public class SuggestionDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	private static final class SuggestionMapper implements RowMapper<Suggestion> {

		@Override
		public Suggestion mapRow(ResultSet rs, int rowNum) throws SQLException {
			Suggestion suggestion = new Suggestion();
			suggestion.setSuggestion_code( rs.getInt( "suggestion_code" ) );
			suggestion.setName( rs.getString( "name" ) );
			suggestion.setEmail( rs.getString( "email" ) );
			suggestion.setMessageType( MessageType.getOpcion( rs.getString( "tipo" ) ) );
			suggestion.setMessage( rs.getString( "message" ) );
			return null;
		}
	}
	
	public List<Suggestion> getSuggestions() {
		return this.jdbcTemplate.query( "SELECT * FROM Suggestion" , new SuggestionMapper() );
	}
	
	public Suggestion getSuggestion( int code ) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM Suggestion WHERE suggestion_code = ?" , new Object[] { code }, new SuggestionMapper() );
	}
	
	public void addSuggestion( Suggestion suggestion ) {
		this.jdbcTemplate.update( "INSERT INTO Suggestion ( suggestion_code, name, email, tipo, message )"
								+ " VALUES ( ?, ?, ?, ?, ? )", suggestion.getSuggestion_code(), suggestion.getName(),
								suggestion.getEmail(), suggestion.getMessageType().toString(), suggestion.getMessage() );
	}
}
