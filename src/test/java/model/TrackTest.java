/**
 * 
 */
package model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 

/**
 * 
 */
public class TrackTest {

	
	@Test
	void testTrackCreationAndGetters() {
		Track track = new Track("Ptolemy", "Aphex Twin","Ambient", 1991);
		
		assertEquals("Aphex Twin", track.getArtist());
		assertEquals("Ptolemy", track.getTitle());
		assertEquals("Ambient", track.getGenre());
		assertEquals(1991, track.getYear());
	}
	
	
}
