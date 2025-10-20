package repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Track;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * ApiTrackRepository is the class that talks to the Discogs API.
 * It sends HTTP requests to the Discogs database and turns the JSON
 * responses into Track objects that the rest of the program can use.
 *
 * This class implements the TrackRepository interface so it can be
 * swapped out for other repository types (like a stub for testing).
 */
public class ApiTrackRepository implements TrackRepository {

	/** Base URL for the Discogs search API */
    private static final String BASE_URL = "https://api.discogs.com/database/search";
    
    /** User-Agent header required by Discogs API */
    private static final String USER_AGENT = "MusicSampleFinder/1.0 +https://github.com/mcorr98/music-sample-finder";

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String token;

    /**
     * Creates a new repository that can query the Discogs API.
     * @param token the personal access token for authenticating with Discogs
     */
    public ApiTrackRepository(String token) {
        this.token = token;
    }

    /**
     * Search for tracks by a given artist name.
     * @param artist the artist to search for
     * @return a list of Track objects matching the artist
     */
    @Override
    public List<Track> findByArtist(String artist) {
        return search("artist=" + encode(artist));
    }

    /**
     * Search for tracks by genre.
     * @param genre the genre to search for
     * @return a list of Track objects matching the genre
     */
    @Override
    public List<Track> findByGenre(String genre) {
        return search("genre=" + encode(genre));
    }

    /**
     * Search for tracks by release title.
     * @param title the release title to search for
     * @return a list of Track objects matching the title
     */
    @Override
    public List<Track> findByTitle(String title) {
        return search("release_title=" + encode(title));
    }

    /**
     * Return a default set of tracks (currently hard-coded to "house" releases).
     * @return a list of Track objects
     */
    @Override
    public List<Track> findAll() {
        return search("q=house&type=release");
    }

    /**
     * Core search method that builds the HTTP request, sends it to Discogs,
     * and parses the JSON response into Track objects.
     * @param query the query string (already URL-encoded)
     * @return a list of Track objects from the API response
     */
    private List<Track> search(String query) {
        try {
            String uri = BASE_URL + "?" + query + "&per_page=20&page=1&token=" + token;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("User-Agent", USER_AGENT)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode root = mapper.readTree(response.body());

            List<Track> tracks = new ArrayList<>();
            JsonNode results = root.path("results");
            if (results.isArray()) {
                for (JsonNode result : results) {
                    tracks.add(parseTrack(result));
                }
            }
            return tracks;
        } catch (Exception e) {
            throw new RuntimeException("API fetch failed", e);
        }
    }

    /**
     * Helper method to turn a single JSON result into a Track object.
     * Handles missing fields and tries different fallbacks for artist and genre.
     * @param result the JSON node representing one search result
     * @return a Track object with title, artist, genre, and year filled in
     */
    private Track parseTrack(JsonNode result) {
        String rawTitle = result.path("title").asText();
        String title = rawTitle;
        String artist = result.path("artist").asText();

        // Try fallbacks for artist
        if (artist.isEmpty()) {
            artist = result.path("artists_sort").asText();
        }

        // If still missing, split "Artist – Release" into parts
        if (artist.isEmpty() && rawTitle.contains(" - ")) {
            String[] parts = rawTitle.split(" - ", 2);
            artist = parts[0].trim();
            title = parts[1].trim();   // keep only the release name
        }

        if (artist.isEmpty()) {
            artist = "Unknown";
        }

        // Genre: prefer genre array, fallback to style
        String genre = "Unknown";
        if (result.has("genre") && result.get("genre").isArray() && result.get("genre").size() > 0) {
            genre = result.get("genre").get(0).asText();
        } else if (result.has("style") && result.get("style").isArray() && result.get("style").size() > 0) {
            genre = result.get("style").get(0).asText();
        }

        int year = result.has("year") ? result.get("year").asInt(0) : 0;

        return new Track(title, artist, genre, year);
    }


    /**
     * Utility method to URL-encode a string for safe use in query parameters.
     * @param value the string to encode
     * @return the encoded string
     */
    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }


}
