package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Album;
import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album getAlbumById(int id);
    List<Album> searchAlbum(String keyword);
    boolean createAlbum(Album album);
    boolean updateAlbum(Album album);
    boolean deleteAlbum(int id);
}
