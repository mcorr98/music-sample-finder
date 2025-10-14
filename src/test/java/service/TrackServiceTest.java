/**
 * 
 */
package service;

import model.Track;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 */
public class TrackServiceTest {

	@Test
	void testFindByArtist() {
		List<Track> tracks = List.of(
				new Track("Aphex Twin", "Ptolemy", "Ambient", 117.65, 1991),
				new Track("Aphex Twin", "Xtal", "Ambient", 114.32, 1991),
				new Track("Decius", "Queen of 14th Street", "House", 124.00, 2025));
		
		TrackService service = new TrackService(tracks);
        List<Track> result = service.findByArtist("Aphex Twin");

		assertEquals(2, result.size());
		assertEquals("Ptolemy", result.get(0).getTitle());
		assertEquals("Xtal", result.get(1).getTitle());
	}
	
	@Test
	void testRecommendRandomTrackNotNull() {
		
		List<Track> tracks = List.of(
				new Track("Aphex Twin", "Ptolemy", "Ambient", 117.65, 1991));
		
		TrackService service = new TrackService(tracks); 
		assertNotNull(service.recommendRandomTrack());
	}
}
