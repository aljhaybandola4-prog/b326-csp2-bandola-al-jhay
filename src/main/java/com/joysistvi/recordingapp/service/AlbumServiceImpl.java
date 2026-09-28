package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.repository.AlbumRepo;
import java.util.List;

public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepo albumRepo;

    public AlbumServiceImpl(AlbumRepo albumRepo) {
        this.albumRepo = albumRepo;
    }

    @Override
    public List<Album> getAllAlbums() { return albumRepo.getAllAlbums(); }

    @Override
    public Album getAlbumById(int id) {
        if (id <= 0) return null;
        return albumRepo.getAlbumById(id);
    }

    @Override
    public List<Album> searchAlbum(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return List.of();
        return albumRepo.searchAlbum(keyword.trim());
    }

    @Override
    public boolean createAlbum(Album album) {
        if (album == null || album.getName() == null || album.getName().trim().isEmpty()
                || album.getYear() <= 0 || album.getArtistId() <= 0) return false;
        album.setName(album.getName().trim());
        return albumRepo.createAlbum(album);
    }

    @Override
    public boolean updateAlbum(Album album) {
        if (album == null || album.getId() <= 0 || album.getName() == null
                || album.getName().trim().isEmpty() || album.getYear() <= 0
                || album.getArtistId() <= 0) return false;
        album.setName(album.getName().trim());
        return albumRepo.updateAlbum(album);
    }

    @Override
    public boolean deleteAlbum(int id) {
        return id > 0 && albumRepo.deleteAlbum(id);
    }
}
