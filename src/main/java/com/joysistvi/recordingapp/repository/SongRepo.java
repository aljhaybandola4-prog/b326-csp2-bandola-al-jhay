package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Song;
import java.util.List;

public interface SongRepo {
    List<Song> getAllSongs();
    Song getSongById(int id);
    List<Song> searchSong(String keyword);
    boolean createSong(Song song);
    boolean updateSong(Song song);
    boolean deleteSong(int id);
}
