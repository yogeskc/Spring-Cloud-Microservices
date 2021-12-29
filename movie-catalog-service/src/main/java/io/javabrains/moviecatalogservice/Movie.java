package io.javabrains.moviecatalogservice;

public class Movie {

	private String moveId; 
	private String name;
	
	
	public Movie() {
		super();
	}
	public String getMoveId() {
		return moveId;
	}
	public void setMoveId(String moveId) {
		this.moveId = moveId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Movie(String moveId, String name) {
		super();
		this.moveId = moveId;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Movie [moveId=" + moveId + ", name=" + name + "]";
	} 

}
