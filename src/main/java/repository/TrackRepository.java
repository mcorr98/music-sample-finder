/**
 * 
 */
package repository;

import model.Track;
import java.util.List;
/**
 * 
 */
public interface TrackRepository {
    List<Track> findAll();
    List<Track> findByArtist(String artist);
    List<Track> findByGenre(String genre);
    List<Track> findByTitle(String title);
}
	
