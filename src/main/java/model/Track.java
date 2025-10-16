/**
 * 
 */
package model;

/**
 * Represents a song ("track") 
 */
public class Track {

	private String artist;
	private String title;
	private String genre;
	private double bpm;
	private int year;

	// Constructor
	public Track(String artist, String title, String genre, double bpm, int year) {
		this.artist = artist;
		this.title = title;
		this.genre = genre;
		this.bpm = bpm;
		this.year = year;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the bpm
	 */
	public double getBpm() {
		return bpm;
	}

	/**
	 * @param bpm the bpm to set
	 */
	public void setBpm(double bpm) {
		this.bpm = bpm;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return String.format("%s by %s [%s, %.1f BPM, %d]", title, artist, genre, bpm, year);
	}

}
