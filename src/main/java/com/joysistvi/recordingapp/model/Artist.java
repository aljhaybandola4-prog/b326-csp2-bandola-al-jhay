package com.joysistvi.recordingapp.model;

public class Artist {

    private int id;
    private String name;

    // Used when adding a new artist (id not yet assigned by the database)
    public Artist(String name) {
        this.name = name;
    }

    // Used when reading from the database or updating an existing artist
    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}