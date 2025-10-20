package testdoubles;

import java.util.List;
import model.Track;
import repository.TrackRepository;

public class StubTrackRepository implements TrackRepository {
    private final List<Track> tracks;

    public StubTrackRepository(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public List<Track> findAll() {
        return tracks;
    }

    @Override
    public List<Track> findByArtist(String artist) {
        return tracks.stream()
                     .filter(t -> t.getArtist().equalsIgnoreCase(artist))
                     .toList();
    }

    @Override
    public List<Track> findByGenre(String genre) {
        return tracks.stream()
                     .filter(t -> t.getGenre().equalsIgnoreCase(genre))
                     .toList();
    }

    @Override
    public List<Track> findByTitle(String title) {
        return tracks.stream()
                     .filter(t -> t.getTitle().equalsIgnoreCase(title))
                     .toList();
    }
}