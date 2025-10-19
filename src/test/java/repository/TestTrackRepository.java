package repository;

import model.Track;
import java.util.List;

public class TestTrackRepository implements TrackRepository {

    private final List<Track> tracks;

    public TestTrackRepository(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public List<Track> findAll() {
        return tracks;
    }
}