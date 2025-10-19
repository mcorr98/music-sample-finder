/**
 * 
 */
package service;

import model.Track;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 */
public class TrackServiceTest {

	private TrackService service;

	@BeforeEach
	void setUp() {
		List<Track> tracks = List.of(new Track("Xtal", "Aphex Twin", "Ambient", 113, 1992),
				new Track("Look Like A Man", "Decius", "House", 124, 2021),
				new Track("Show Me No Tears", "Decius", "House", 126, 2021),
				new Track("Don’t Worry About It", "Fantastic Man", "Deep House", 120, 2016),
				new Track("Cloud Manager", "Fantastic Man", "Deep House", 118, 2018),
				new Track("Healing Feeling", "Tornado Wallace", "Disco", 115, 2013),
				new Track("Today", "Tornado Wallace", "House", 122, 2017));
		service = new TrackService(tracks);
	}

	@Test
	void findByArtist_returnsAllTracksByThatArtist() {
		List<Track> results = service.findByArtist("Decius");
		assertEquals(2, results.size());
		assertTrue(results.stream().allMatch(t -> t.getArtist().equals("Decius")));
	}

	@Test
	void findByGenre_returnsCorrectTracks() {
		List<Track> results = service.findByGenre("House");
		assertEquals(3, results.size());
		assertTrue(results.stream().allMatch(t -> t.getGenre().equals("House")));
	}

	@Test
	void findByTitle_handlesExactMatch() {
		List<Track> results = service.findByTitle("Xtal");
		assertEquals(1, results.size());
		assertEquals("Aphex Twin", results.get(0).getArtist());
	}

	@Test
	void recommendRandomTrack_returnsNonNullAndFromList() {
		Track rec = service.recommendRandomTrack();
		assertNotNull(rec);
		assertTrue(service.getTracks().contains(rec));
	}

}
