package cli;

import model.Track;
import org.junit.jupiter.api.Test;

import repository.TestTrackRepository;
import service.TrackService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    private String runWithInput(String input) {
        List<Track> tracks = List.of(
                new Track("Xtal", "Aphex Twin", "Ambient", 113, 1992),
                new Track("Look Like A Man", "Decius", "House", 124, 2021),
                new Track("Don’t Worry About It", "Fantastic Man", "Deep House", 120, 2016),
                new Track("Healing Feeling", "Tornado Wallace", "Disco", 115, 2013)
        );
        TrackService service = new TrackService(new TestTrackRepository(tracks).findAll());

        // Simulate user input
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        // Capture program output
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));

        App.run(service);

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
}
