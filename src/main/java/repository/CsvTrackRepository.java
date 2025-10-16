/**
 * 
 */
package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Track;

/**
 * 
 */
public class CsvTrackRepository implements TrackRepository {

	private final String filePath;

	/**
	 * Constructor
	 * @param filePath - path to track data resource (currently csv file)
	 */
	public CsvTrackRepository(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Gets all tracks from data and adds them to a list of tracks
	 */
	@Override
	public List<Track> findAll() {

		List<Track> tracks = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;

			while ((line = br.readLine()) != null) {

				String[] parts = line.split(",");

				if (parts.length == 5) {
					tracks.add(new Track(parts[0].trim(), parts[1].trim(), parts[2].trim(),
							safeParseDouble(parts[3].trim(), 0.0), safeParseInt(parts[4].trim(),0)));
				}

			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return tracks;
	}

	/**
	 * Method to validate double parsing, setting to fallback value if there's an error
	 * @param s - string from data resource to be parsed to double
	 * @param fallback - value to be  used in case of parsing failure
	 * @return parsed double or fallback value
	 */
	private double safeParseDouble(String s, double fallback) {
		try {
			return Double.parseDouble(s.trim());
		} catch (Exception e) {
			return fallback;
		}
	}

	/**
	 * Method to validate int parsing, setting to fallback value if there's an error
	 * @param s - string to be parsed to int
	 * @param fallback - fallback value to be used if parsing fails 
	 * @return returns parsed int or fallback value in case of parsing failure
	 */
	private int safeParseInt(String s, int fallback) {
		try {
			return Integer.parseInt(s.trim());
		} catch (Exception e) {
			return fallback;
		}
	}

}
