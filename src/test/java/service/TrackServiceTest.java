/**
 * 
 */
package service;

import model.Track;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import repository.TrackRepository;
import testdoubles.StubTrackRepository;

/**
 * 
 */
public class TrackServiceTest {

	private TrackService service;

	@BeforeEach
	void setUp() {
		List<Track> tracks = List.of(
                new Track("Xtal", "Aphex Twin", "Ambient", 1992),
                new Track("Look Like A Man", "Decius", "House", 2021),
                new Track("Don’t Worry About It", "Fantastic Man", "House", 2016),
                new Track("Healing Feeling", "Tornado Wallace", "Disco", 2013)
        );

		TrackRepository repo = new StubTrackRepository(tracks);
		service = new TrackService(repo);
	}

	@Test
	void testFindByArtist() {
		List<Track> results = service.findByArtist("Aphex Twin");
		assertEquals(1, results.size());
	}

	@Test
	void findByGenre_returnsCorrectTracks() {
		List<Track> results = service.findByGenre("House");
		assertEquals(2, results.size());
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
		assertTrue(List.of("Xtal", "Look Like A Man", "Don’t Worry About It", "Healing Feeling").contains(rec.getTitle()));
	}
}
