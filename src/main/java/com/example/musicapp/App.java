package com.example.musicapp;

import repository.CsvTrackRepository;
import repository.TrackRepository;
import service.TrackService;

/**
 * 
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	TrackRepository repo = new CsvTrackRepository();
    	TrackService service = new TrackService(repo.findAll());
    	
        System.out.println( "Crate digger starting..." );
        
        System.out.println("Random reccommendend track: " + service.recommendRandomTrack());
        System.out.println("All house tracks: " + service.findByGenre("House"));
    }
}
