/**
 * 
 */
package service;

import java.util.*;
import java.util.stream.Collectors;

import model.Track;

/**
 * 
 */
public class TrackService {
	
	private final List<Track> tracks; 
	
	//Constructor for TrackService
	public TrackService(List<Track> tracks) {
		this.tracks = new ArrayList<>(tracks);
	}
	
	//Finds all tracks by a given artist
	public List<Track> findByArtist(String artist) {
		return tracks.stream()
				.filter(t -> t.getArtist().equalsIgnoreCase(artist))
				.collect(Collectors.toList());
	}
	
	//Recommends a random track
	public Track recommendRandomTrack() {
		if (tracks.isEmpty()) return null;
		
		Random rand = new Random();
		return tracks.get(rand.nextInt(tracks.size()));
	}
	
	public List<Track> findByGenre(String genre){
		return tracks.stream()
				.filter(t -> t.getGenre().equalsIgnoreCase(genre))
				.collect(Collectors.toList());
	}

}
