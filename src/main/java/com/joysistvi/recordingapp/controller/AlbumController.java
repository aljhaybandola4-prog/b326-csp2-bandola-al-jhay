package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.service.AlbumService;
import java.util.List;

public class AlbumController {
    private final AlbumService albumService;
    public AlbumController(AlbumService albumService) { this.albumService = albumService; }

    public List<Album> handleViewAllAlbums() { return albumService.getAllAlbums(); }
    public Album handleGetAlbumById(int id) { return albumService.getAlbumById(id); }
    public List<Album> searchAlbum(String keyword) { return albumService.searchAlbum(keyword); }
    public boolean handleCreateAlbum(Album album) { return albumService.createAlbum(album); }
    public boolean handleUpdateAlbum(Album album) { return albumService.updateAlbum(album); }
    public boolean handleDeleteAlbum(int id) { return albumService.deleteAlbum(id); }
}
