package cli;

import java.util.List;
import java.util.Scanner;

import model.Track;
import repository.CsvTrackRepository;
import repository.TrackRepository;
import service.TrackService;

public class App {

	public static void main(String[] args) {
		TrackRepository repo = new CsvTrackRepository("tracks.csv");
		TrackService service = new TrackService(repo.findAll());

		// Testable method
		run(service);
	}

	public static void run(TrackService service) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to your AI powered Crate Digger.");
		System.out.println("Commands: artist <name>, genre <name>, title <name>, recommend, quit");

		while (true) {
			System.out.print("> ");
			String input = scanner.nextLine().trim();

			if (input.equalsIgnoreCase("quit")) {
				break;
			}

			String[] parts = input.split(" ", 2);
			String command = parts[0].toLowerCase();

			switch (command) {
			case "artist":
				if (parts.length < 2) {
					System.out.println("Expected format: artist <name>");
					continue;
				}
				printResults(service.findByArtist(parts[1]), "artist", parts[1]);
				continue;

			case "genre":
				if (parts.length < 2) {
					System.out.println("Expected format: genre <name>");
					continue;
				}
				printResults(service.findByGenre(parts[1]), "genre", parts[1]);
				continue;

			case "title":
				if (parts.length < 2) {
					System.out.println("Expected format: title <name>");
					continue;
				}
				printResults(service.findByTitle(parts[1]), "title", parts[1]);
				continue;

			case "recommend":
				Track rec = service.recommendRandomTrack();
				System.out.println("Try this one: " + rec);
				continue;

			default:
				System.out.println("Unknown command: " + command);
				continue;
			}
		}

		System.out.println("Goodbye!");
		scanner.close();
	}

	/**
	 * Helper method for handling printing results
	 * 
	 * @param tracks  - List of Track objects resulting from the query
	 * @param command - String, the command chosen by user
	 * @param query   - String, the search term input by user
	 */
	private static void printResults(List<Track> tracks, String command, String query) {
		if (tracks.isEmpty()) {
			System.out.println("No tracks found for " + command + ": " + query);
		} else {
			for (Track track : tracks) {
				System.out.println(track);
			}
		}
	}
}
