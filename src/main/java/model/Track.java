/**
 * 
 */
package model;

/**
 * Represents a single piece of music (a "track").
 * POJO used throughout the application to pass track information
 * between the repository, service, and CLI layers.
 */
public class Track {

	private String artist;
	private String title;
	private String genre;
	private int year;

	 /**
     * Creates a new Track with all its details.
     *
     * @param title  the title of the track
     * @param artist the artist who created the track
     * @param genre  the genre of the track (e.g. "Electronic", "Rock")
     * @param year   the year the track was released (0 if unknown)
     */
	public Track(String title, String artist, String genre, int year) {
		this.artist = artist;
		this.title = title;
		this.genre = genre;
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

	/**
     * Returns a formatted string representation of the track,
     * showing its title, artist, genre, and year.
     *
     * @return a string like "Song Title by Artist [Genre, 2020]"
     */
	@Override
	public String toString() {
		return String.format("%s by %s [%s, %d]", title, artist, genre, year);
	}

}
