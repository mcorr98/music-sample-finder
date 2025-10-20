package cli;

import model.Track;
import org.junit.jupiter.api.Test;
import service.TrackService;
import testdoubles.StubTrackRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CliRunnerTest {

    private String runWithInput(String input) {
        List<Track> tracks = List.of(
                new Track("Xtal", "Aphex Twin", "Ambient", 1992),
                new Track("Look Like A Man", "Decius", "House", 2021),
                new Track("Don’t Worry About It", "Fantastic Man", "Deep House", 2016),
                new Track("Healing Feeling", "Tornado Wallace", "Disco", 2013)
        );
        TrackService service = new TrackService(new StubTrackRepository(tracks));

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));

        new CliRunner(service).run();

        return out.toString();
    }

    @Test
    void testQuitCommandExits() {
        String output = runWithInput("quit\n");
        assertTrue(output.contains("Goodbye!"));
    }

    @Test
    void testArtistCommand() {
        String output = runWithInput("artist Decius\nquit\n");
        assertTrue(output.contains("Look Like A Man"));
    }

    @Test
    void testGenreCommand() {
        String output = runWithInput("genre Deep House\nquit\n");
        assertTrue(output.contains("Don’t Worry About It"));
    }

    @Test
    void testTitleCommand() {
        String output = runWithInput("title Xtal\nquit\n");
        assertTrue(output.contains("Aphex Twin"));
    }

    @Test
    void testRecommendCommand() {
        String output = runWithInput("recommend\nquit\n");
        assertTrue(output.contains("Try this one:"));
    }

    @Test
    void testUnknownCommand() {
        String output = runWithInput("tiitlee\nquit\n");
        assertTrue(output.contains("Unknown command"));
    }

    // 🔥 New edge‑case tests

    @Test
    void testArtistCommandCaseInsensitive() {
        String output = runWithInput("artist decius\nquit\n");
        assertTrue(output.contains("Look Like A Man"), "Artist search should be case‑insensitive");
    }

    @Test
    void testGenreCommandNoResults() {
        String output = runWithInput("genre Techno\nquit\n");
        assertTrue(output.contains("No tracks found") || output.contains("0 results"),
                "Should handle no results gracefully");
    }

    @Test
    void testTitleCommandNoResults() {
        String output = runWithInput("title Nonexistent\nquit\n");
        assertTrue(output.contains("No tracks found") || output.contains("0 results"),
                "Should handle no results gracefully");
    }

    @Test
    void testMultipleCommandsInOneSession() {
        String output = runWithInput("artist Aphex Twin\ngenre Disco\nquit\n");
        assertTrue(output.contains("Xtal"));
        assertTrue(output.contains("Healing Feeling"));
    }
}
