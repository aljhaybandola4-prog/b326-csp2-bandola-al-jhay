package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtists();

    List<Artist> searchArtist(String keyword);

    Artist getArtistById(int id);

    boolean addArtist(Artist artist);

    boolean updateArtist(Artist artist);

    boolean deleteArtist(int id);

    boolean archiveArtist(int id);

    boolean restoreArtist(int id);

    List<Artist> getArchivedArtists();
}