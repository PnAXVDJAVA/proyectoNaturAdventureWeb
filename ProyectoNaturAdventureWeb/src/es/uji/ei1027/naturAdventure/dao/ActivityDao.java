package es.uji.ei1027.naturAdventure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Level;
import es.uji.ei1027.naturAdventure.service.CodeGetter;

@Repository
public class ActivityDao {
	
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
	
	private static final class ActivityMapper implements RowMapper<Activity> {
		public Activity mapRow( ResultSet rs, int rowNum ) throws SQLException {
			Activity activity = new Activity();
			activity.setCodActivity( rs.getInt( "codactivity" ) );
			activity.setName( rs.getString( "name" ) );
			activity.setDescription( rs.getString( "description" ) );
			activity.setDuration( rs.getInt( "duration" ) );
			activity.setPricePerPerson( rs.getDouble( "priceperperson" ));
			activity.setMinPartakers( rs.getInt( "minpartakers" ) );
			activity.setMaxPartakers( rs.getInt( "maxpartakers" ) );
			activity.setLevel( Level.getOpcion( rs.getString( "level" ) ) );
			activity.setPicture( rs.getBytes( "picture" ) );
			return activity;
		}
	}
	
	public List<Activity> getActivities() {
		return this.jdbcTemplate.query( "SELECT * FROM Activity", new ActivityMapper() );
	}
	
	public Activity getActivity( int codActivity ) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM Activity WHERE codActivity = ?" ,  new Object[] { codActivity }, new ActivityMapper() );
	}
	
	public void addActivity( Activity activity ) {
		int nextCodActivity = this.codeGetter.getNextCode( "codActivity" , "Activity" );
		this.jdbcTemplate.update( "INSERT INTO Activity ( codActivity, name, description, pricePerPerson, level, duration, maxpartakers, minpartakers, picture ) VALUES( ?, ?, ?, ?, cast(? as ActivityLevel), ?, ?, ?, ? ) ",
				nextCodActivity, activity.getName(), activity.getDescription(), activity.getPricePerPerson(), activity.getLevel().toString(), activity.getDuration(), activity.getMaxPartakers(), activity.getMinPartakers(), activity.getPicture() );
	}

	public void updateActivity( Activity activity ) {
		this.jdbcTemplate.update( "UPDATE Activity SET name = ?, description = ?, pricePerPerson = ?, level = cast(? as ActivityLevel), duration = ?, maxpartakers = ?, minpartakers = ?, picture = ? WHERE codActivity = ?", 
									activity.getName(), activity.getDescription(), activity.getPricePerPerson(), activity.getLevel().toString(), activity.getDuration(), activity.getMaxPartakers(), activity.getMinPartakers(), activity.getPicture(), activity.getCodActivity() );
	}
	
	public void deleteActivity( Activity activity ) {
		this.jdbcTemplate.update( "DELETE FROM Activity WHERE codActivity = ?", activity.getCodActivity() );
	}
	
	public void addSpecializedInstructor( int codActivity, String instructorNif ) {
		this.jdbcTemplate.update( "INSERT INTO Specialized ( codActivity, instructorNif ) VALUES ( ?, ? )",
									codActivity, instructorNif );
	}
	
	public void removeSpecializedInstructor( int codActivity, String instructorNif ) {
		this.jdbcTemplate.update( "DELETE FROM Specialized WHERE codActivity = ? AND instructorNif = ?", 
				codActivity, instructorNif );
	}

}
