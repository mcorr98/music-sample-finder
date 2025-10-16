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
	
	/**
	 * Method which finds all tracks by a given artist 
	 * @param artist - artist to filter by 
	 * @return - list of Tracks by the given artist
	 */
	public List<Track> findByArtist(String artist) {
		return tracks.stream()
				.filter(t -> t.getArtist().equalsIgnoreCase(artist))
				.collect(Collectors.toList());
	}
	
	/**
	 * Method which suggests a random track from the list 
	 * @return a track chosen at random
	 */
	public Track recommendRandomTrack() {
		if (tracks.isEmpty()) return null;
		
		Random rand = new Random();
		return tracks.get(rand.nextInt(tracks.size()));
	}
	
	/**
	 * Method which finds all tracks of a given genre 
	 * @param genre - genre to filter by 
	 * @return - list of Tracks in the given genre
	 */
	public List<Track> findByGenre(String genre){
		return tracks.stream()
				.filter(t -> t.getGenre().equalsIgnoreCase(genre))
				.collect(Collectors.toList());
	}

}
