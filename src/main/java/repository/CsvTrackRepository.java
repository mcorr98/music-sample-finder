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

	public CsvTrackRepository(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public List<Track> findAll() {

		List<Track> tracks = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;

			while ((line = br.readLine()) != null) {

				String[] parts = line.split(",");

				if (parts.length == 5) {
					tracks.add(new Track(parts[0].trim(), parts[1].trim(), parts[2].trim(),
							Double.parseDouble(parts[3].trim()), Integer.parseInt(parts[4].trim())));
				}

			}
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		return tracks;
	}

}
