package es.uji.ei1027.naturAdventure.domain;

import java.util.LinkedList;
import java.util.List;

public class Degree {
	private String description;
	private String name;
	private int codDegree;
	private List<Instructor> instructors;
	
	public Degree() {
		this.codDegree = -1;
		this.description = null;
		this.name = null;
		this.instructors = null;
	}

	public int getCodDegree() {
		return codDegree;
	}

	public void setCodDegree(int codDegree) {
		this.codDegree = codDegree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Instructor> getInstructors() {
		return this.instructors;
	}
	
	public void setInstructors( List<Instructor> listInstructors ) {
		this.instructors = listInstructors;
	}
	
	public void addInstructor( Instructor instructor ) {
		if( this.instructors == null ) {
			this.instructors = new LinkedList<Instructor>();
		}
		this.instructors.add( instructor );
	}

	@Override
	public String toString() {/////////////////////////////////////////////////////////////////////////////////////
		return "Cod: " + codDegree + "; Name: " + name + "; Description: " + description;
	}
}
