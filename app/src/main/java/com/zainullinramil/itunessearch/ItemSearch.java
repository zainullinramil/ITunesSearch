package com.zainullinramil.itunessearch;

/**
 * Created by user on 30.03.2016.
 */
public class ItemSearch {
    private String artistName;
    private String collectionName;
    private String trackName;
    private String artworkUrl100;


    public ItemSearch(String artistName, String collectionName, String trackName, String artworkUrl100) {
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackName = trackName;
        this.artworkUrl100 = artworkUrl100;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }
}
