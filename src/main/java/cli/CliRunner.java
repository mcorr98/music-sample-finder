package cli;

import model.Track;
import service.TrackService;

import java.util.List;
import java.util.Scanner;

/**
 * CliRunner is the entry point for the command-line interface (CLI).
 * It handles user input, interprets commands, and prints results to the console.
 *
 * This class connects the user-facing CLI with the TrackService,
 * which actually fetches and recommends tracks.
 */
public class CliRunner {
	private final TrackService service;

	/**
     * Create a new CLI runner with a given TrackService.
     * @param service the service used to search and recommend tracks
     */
	public CliRunner(TrackService service) {
		this.service = service;
	}

	 /**
     * Starts the main CLI loop.
     * Prints a welcome message, shows available commands,
     * and keeps reading input until the user types "quit".
     */
	public void run() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Crate Digger.");
		System.out.println("Commands: artist <name>, genre <name>, title <name>, recommend, quit");

		while (true) {
			System.out.print("> ");
			String input = scanner.nextLine().trim();
			if (input.equalsIgnoreCase("quit"))
				break;

			handleCommand(input);
			System.out.println();
		}
		System.out.println("Thanks for digging with Crate Digger. Goodbye!");
		scanner.close();
	}

	/**
     * Interprets a single line of user input and calls the right service method.
     * Supports commands: artist, genre, title, recommend.
     * @param input the raw command line entered by the user
     */
	private void handleCommand(String input) {
	    String[] parts = input.split(" ", 2);
	    String command = parts[0].toLowerCase();

	    switch (command) {
	        case "artist": {
	            if (parts.length < 2) {
	                System.out.println("Expected format: artist <name>");
	            } else {
	            	System.out.println("Searching for artist: " + parts[1]);
	                printResults(service.findByArtist(parts[1]));
	            }
	            break;
	        }
	        case "genre": {
	            if (parts.length < 2) {
	                System.out.println("Expected format: genre <name>");
	            } else {
	            	System.out.println("Searching for genre: " + parts[1]);
	                printResults(service.findByGenre(parts[1]));
	            }
	            break;
	        }
	        case "title": {
	            if (parts.length < 2) {
	                System.out.println("Expected format: title <name>");
	            } else {
	            	System.out.println("Searching for title: " + parts[1]);
	                printResults(service.findByTitle(parts[1]));
	            }
	            break;
	        }
	        case "recommend": {
	        	Track rec = service.recommendRandomTrack();
	            if (rec == null) {
	                System.out.println("No tracks available to recommend.");
	            } else {
	                System.out.println("Try this one: " + rec);
	            }
	            break;
	        }
	        default:
	            System.out.println("Unknown command");
	    }
	}

	/**
     * Prints a list of tracks to the console.
     * If the list is empty, prints "No tracks found".
     * @param tracks the list of Track objects to display
     */
	private void printResults(List<Track> tracks) {
	    if (tracks.isEmpty()) {
	        System.out.println("No tracks found");
	    } else {
	        System.out.printf("%-25s | %-40s | %-12s | %s%n",
	                "Artist", "Title", "Genre", "Year");
	        System.out.println("------------------------------------------------------------------------------------------------");
	        for (Track track : tracks) {
	            System.out.printf("%-25s | %-60s | %-15s | %4d%n",
	                    track.getArtist(),
	                    truncate(track.getTitle(), 40),
	                    track.getGenre(),
	                    track.getYear());
	        }
	    }
	}
	
	/**
	 * Shortens a string to fit within a maximum length.
	 * If the string is longer than  maxLength, it is cut off
	 * and "..."is added at the end so the user can see it was shortened.
	 * If the string is already short enough, it is returned unchanged.
	 * @param text the original string to check
	 * @param maxLength the maximum number of characters allowed
	 * @return the original string if it fits, or a shortened version with "..." appended
	 */
	private String truncate(String text, int maxLength) {
	    return text.length() <= maxLength ? text : text.substring(0, maxLength - 3) + "...";
	}
}