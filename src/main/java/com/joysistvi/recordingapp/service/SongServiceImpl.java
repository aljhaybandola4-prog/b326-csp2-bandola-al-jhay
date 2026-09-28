package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.repository.SongRepo;
import java.util.List;

public class SongServiceImpl implements SongService {
    private final SongRepo songRepo;

    public SongServiceImpl(SongRepo songRepo) { this.songRepo = songRepo; }

    @Override public List<Song> getAllSongs() { return songRepo.getAllSongs(); }
    @Override public Song getSongById(int id) { return id > 0 ? songRepo.getSongById(id) : null; }
    @Override public List<Song> searchSong(String keyword) {
        return keyword == null || keyword.trim().isEmpty() ? List.of() : songRepo.searchSong(keyword.trim());
    }
    @Override public boolean createSong(Song song) {
        if (song == null || blank(song.getTitle()) || blank(song.getLength()) || blank(song.getGenre())
                || song.getAlbumId() <= 0) return false;
        return songRepo.createSong(song);
    }
    @Override public boolean updateSong(Song song) {
        if (song == null || song.getId() <= 0 || blank(song.getTitle()) || blank(song.getLength())
                || blank(song.getGenre()) || song.getAlbumId() <= 0) return false;
        return songRepo.updateSong(song);
    }
    @Override public boolean deleteSong(int id) { return id > 0 && songRepo.deleteSong(id); }
    private boolean blank(String value) { return value == null || value.trim().isEmpty(); }
}
