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
import es.uji.ei1027.naturAdventure.service.CodeGetter;

@Repository
public class SuggestionDao {

	private JdbcTemplate jdbcTemplate;
	private CodeGetter codeGetter;
	
	@Autowired
	public void setDataSource( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	@Autowired
	public void setCodeGetter( CodeGetter codeGetter ) {
		this.codeGetter = codeGetter;
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
			return suggestion;
		}
	}
	
	public List<Suggestion> getSuggestions() {
		return this.jdbcTemplate.query( "SELECT * FROM Suggestion" , new SuggestionMapper() );
	}
	
	public Suggestion getSuggestion( int code ) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM Suggestion WHERE suggestion_code = ?" , new Object[] { code }, new SuggestionMapper() );
	}
	
	public void addSuggestion( Suggestion suggestion ) {
		int nextCodSuggestion = this.codeGetter.getNextCode( "suggestion_code" , "Suggestion" );
		this.jdbcTemplate.update( "INSERT INTO Suggestion ( suggestion_code, name, email, tipo, message )"
								+ " VALUES ( ?, ?, ?, cast(? as MessageType), ? )", nextCodSuggestion, suggestion.getName(),
								suggestion.getEmail(), suggestion.getMessageType().toString(), suggestion.getMessage() );
	}
}
