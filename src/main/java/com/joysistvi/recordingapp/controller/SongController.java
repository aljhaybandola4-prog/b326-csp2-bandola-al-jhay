package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.SongService;
import java.util.List;

public class SongController {
    private final SongService songService;
    public SongController(SongService songService) { this.songService = songService; }

    public List<Song> handleViewAllSongs() { return songService.getAllSongs(); }
    public Song handleGetSongById(int id) { return songService.getSongById(id); }
    public List<Song> searchSong(String keyword) { return songService.searchSong(keyword); }
    public boolean handleCreateSong(Song song) { return songService.createSong(song); }
    public boolean handleUpdateSong(Song song) { return songService.updateSong(song); }
    public boolean handleDeleteSong(int id) { return songService.deleteSong(id); }
}
