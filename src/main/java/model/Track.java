/**
 * 
 */
package model;

/**
 * 
 */
public class Track {

	private String artist; 
	private String title;
	private String genre;
	private double bpm;
	private int year;
	
	//Getters and setters 
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public double getBpm() {
		return bpm;
	}
	public void setBpm(double bpm) {
		this.bpm = bpm;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	//Constructor
	public Track(String artist, String title, String genre, double bpm, int year) {
		this.artist = artist;
		this.title = title;
		this.genre = genre;
		this.bpm = bpm;
		this.year = year;
	} 

}
