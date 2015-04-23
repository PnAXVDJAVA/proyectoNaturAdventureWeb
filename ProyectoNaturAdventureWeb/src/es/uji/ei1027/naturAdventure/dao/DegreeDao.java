package es.uji.ei1027.naturAdventure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.naturAdventure.domain.Degree;

@Repository
public class DegreeDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	private static final class DegreeMapper implements RowMapper<Degree> {
		@Override
		public Degree mapRow( ResultSet rs, int rowNum ) throws SQLException {
			Degree degree = new Degree();
			degree.setCodDegree( rs.getInt( "coddegree" ) );
			degree.setName( rs.getString( "name" ) );
			degree.setDescription( rs.getString( "description" ) );
			return degree;
		}
	}
	
	public List<Degree> getDegrees() {
		return this.jdbcTemplate.query( "SELECT * FROM Degree" , new DegreeMapper() );
	}
	
	public Degree getDegree( int codDegree ) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM Degree WHERE codDegree = ?", new Object[] { codDegree }, new DegreeMapper() );
	}
	
	public void addDegree( Degree degree ) {
		this.jdbcTemplate.update( "INSERT INTO Degree ( coddegree, name, description ) VALUES( ?, ?, ? )",
									degree.getCodDegree(), degree.getName(), degree.getDescription() );
	}
	
	public void updateDegree( Degree degree ) {
		this.jdbcTemplate.update( "UPDATE Degree SET name = ?, description = ? WHERE coddegree = ?",  
									degree.getName(), degree.getDescription(), degree.getCodDegree() );
	}
	
	public void deleteDegree( Degree degree ) {
		this.jdbcTemplate.update( "DELETE FROM Degree WHERE coddegree = ?", degree.getCodDegree() );
	}
	
}
