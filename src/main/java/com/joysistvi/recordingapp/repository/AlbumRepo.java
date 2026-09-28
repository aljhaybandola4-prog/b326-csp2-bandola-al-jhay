package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Album;
import java.util.List;

public interface AlbumRepo {
    List<Album> getAllAlbums();
    Album getAlbumById(int id);
    List<Album> searchAlbum(String keyword);
    boolean createAlbum(Album album);
    boolean updateAlbum(Album album);
    boolean deleteAlbum(int id);
}
