package service;

import model.Track;
import repository.TrackRepository;

import java.util.List;
import java.util.Random;

/**
 * TrackService sits between the CLI and the repository. It delegates search
 * requests to the TrackRepository and also provides extra logic such as
 * recommending a random track.
 */
public class TrackService {
	private final TrackRepository repository;
	private final Random random = new Random();

	/**
	 * Creates a new TrackService that uses the given repository.
	 *
	 * @param repository the repository that provides access to tracks
	 */
	public TrackService(TrackRepository repository) {
		this.repository = repository;
	}

	/**
	 * Find tracks by a given artist name.
	 *
	 * @param artist the artist to search for
	 * @return a list of tracks by that artist (may be empty if none found)
	 */
	public List<Track> findByArtist(String artist) {
		return repository.findByArtist(artist);
	}

	/**
	 * Find tracks by genre.
	 *
	 * @param genre the genre to search for
	 * @return a list of tracks in that genre (may be empty if none found)
	 */
	public List<Track> findByGenre(String genre) {
		return repository.findByGenre(genre);
	}

	/**
	 * Find tracks by release title.
	 *
	 * @param title the release title to search for
	 * @return a list of tracks with that title (may be empty if none found)
	 */
	public List<Track> findByTitle(String title) {
		return repository.findByTitle(title);
	}

	/**
	 * Recommend a random track from the repository.
	 * This method fetches all available tracks and picks one at random. If no
	 * tracks are available, it returns null
	 *
	 * @return a randomly chosen Track, or null if the repository is empty
	 */
	public Track recommendRandomTrack() {
		List<Track> all = repository.findAll();
		return all.isEmpty() ? null : all.get(random.nextInt(all.size()));
	}
}
