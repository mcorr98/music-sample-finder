package integration;

import model.Track;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import repository.ApiTrackRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class DiscogsIntegrationTest {

    private String token() {
        String token = System.getenv("DISCOGS_TOKEN");
        assumeTrue(token != null && !token.isBlank(),
                "DISCOGS_TOKEN must be set for integration tests");
        return token;
    }

    @Test
    @Tag("integration")
    void testFindByArtistReturnsResults() {
        ApiTrackRepository repo = new ApiTrackRepository(token());
        List<Track> results = repo.findByArtist("Decius");
        assertFalse(results.isEmpty(), "Expected results for Decius");
    }

    @Test
    @Tag("integration")
    void testFindByTitleReturnsResults() {
        ApiTrackRepository repo = new ApiTrackRepository(token());
        List<Track> results = repo.findByTitle("Homework");
        assertFalse(results.isEmpty(), "Expected results for Homework");
    }

    @Test
    @Tag("integration")
    void testFindByGenreReturnsResults() {
        ApiTrackRepository repo = new ApiTrackRepository(token());
        List<Track> results = repo.findByGenre("House");
        assertFalse(results.isEmpty(), "Expected results for House genre");
    }

    @Test
    @Tag("integration")
    void testEmptyArtistReturnsEmptyList() {
        ApiTrackRepository repo = new ApiTrackRepository(token());
        List<Track> results = repo.findByArtist("");
        assertTrue(results.isEmpty(), "Empty artist query should return empty list");
    }

    @Test
    @Tag("integration")
    void testSpecialCharacterArtistDoesNotCrash() {
        ApiTrackRepository repo = new ApiTrackRepository(token());
        // Björk has an accented character — ensures URL encoding works
        List<Track> results = repo.findByArtist("Björk");
        assertNotNull(results, "Results should not be null");
        // We don’t assert non‑empty because Discogs data can vary
    }

    @Test
    @Tag("integration")
    void testBadTokenThrowsOrFailsGracefully() {
        ApiTrackRepository repo = new ApiTrackRepository("not-a-real-token");
        try {
            List<Track> results = repo.findByArtist("Apex Twin");
            // Depending on your implementation, this may throw or return empty
            assertTrue(results.isEmpty(), "Bad token should not return real results");
        } catch (RuntimeException e) {
            // Acceptable outcome too — just proves it fails gracefully
            assertTrue(e.getMessage().toLowerCase().contains("unauthorized")
                    || e.getMessage().toLowerCase().contains("token"));
        }
    }
}
