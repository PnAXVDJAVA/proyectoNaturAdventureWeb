package es.uji.ei1027.naturAdventure.domain;

import java.util.LinkedList;
import java.util.List;

public class Activity {
	
	int codActivity;
	String name;
	String description;
	double pricePerPerson;
	int duration;
	int maxPartakers;
	int minPartakers;
	Level level;
	byte [] picture;
	String pictureString;
	List<Instructor> specializedInstructors;
	
	public int getCodActivity() {
		return codActivity;
	}

	public void setCodActivity(int codActivity) {
		this.codActivity = codActivity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPricePerPerson() {
		return pricePerPerson;
	}

	public void setPricePerPerson(double pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getMaxPartakers() {
		return maxPartakers;
	}

	public void setMaxPartakers(int maxPartakers) {
		this.maxPartakers = maxPartakers;
	}

	public int getMinPartakers() {
		return minPartakers;
	}

	public void setMinPartakers(int minPartakers) {
		this.minPartakers = minPartakers;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getPictureString() {
		return pictureString;
	}

	public void setPictureString(String pictureString) {
		this.pictureString = pictureString;
	}

	public void setSpecializedInstructors(  List<Instructor> list ) {
		this.specializedInstructors = list;
	}
	
	public List<Instructor> getSpecializedInstructors() {
		return this.specializedInstructors;
	}
	
	public void addSpecializedInstructor(  Instructor instructor ) {
		if( specializedInstructors == null ) {
			this.specializedInstructors = new LinkedList<>();
		}
		specializedInstructors.add( instructor );
	}

	@Override
	public String toString() {
		return name + "\n" + description + "\n" + "Level: " + level + "--Duration: " + duration + "--MaxPartakers: " 
				+ maxPartakers + "--MinPartakers: " + minPartakers;
	}
}
