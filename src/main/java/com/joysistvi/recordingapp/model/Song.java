package com.joysistvi.recordingapp.model;

public class Song {
    private int id;
    private String title;
    private String length;
    private String genre;
    private int albumId;

    public Song(String title, String length, String genre, int albumId) {
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.albumId = albumId;
    }

    public Song(int id, String title, String length, String genre, int albumId) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.albumId = albumId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLength() { return length; }
    public void setLength(String length) { this.length = length; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getAlbumId() { return albumId; }
    public void setAlbumId(int albumId) { this.albumId = albumId; }
}
