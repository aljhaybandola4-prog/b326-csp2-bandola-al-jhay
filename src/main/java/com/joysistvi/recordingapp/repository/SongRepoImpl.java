package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Song;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepoImpl implements SongRepo {
    private final DbConnection dbConnection;

    public SongRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT id, title, length, genre, album_id FROM songs ORDER BY id";
        try (Connection conn = dbConnection.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                songs.add(new Song(rs.getInt("id"), rs.getString("title"),
                        rs.getString("length"), rs.getString("genre"), rs.getInt("album_id")));
            }
        } catch (SQLException e) {
            System.err.println("Get All Songs Error: " + e.getMessage());
        }
        return songs;
    }

    @Override
    public Song getSongById(int id) {
        String query = "SELECT id, title, length, genre, album_id FROM songs WHERE id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Song(rs.getInt("id"), rs.getString("title"),
                            rs.getString("length"), rs.getString("genre"), rs.getInt("album_id"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Get Song By ID Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Song> searchSong(String keyword) {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT id, title, length, genre, album_id FROM songs WHERE title LIKE ? ORDER BY id";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songs.add(new Song(rs.getInt("id"), rs.getString("title"),
                            rs.getString("length"), rs.getString("genre"), rs.getInt("album_id")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Search Song Error: " + e.getMessage());
        }
        return songs;
    }

    @Override
    public boolean createSong(Song song) {
        String query = "INSERT INTO songs (title, length, genre, album_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, song.getTitle());
            ps.setString(2, song.getLength());
            ps.setString(3, song.getGenre());
            ps.setInt(4, song.getAlbumId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Create Song Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateSong(Song song) {
        String query = "UPDATE songs SET title = ?, length = ?, genre = ?, album_id = ? WHERE id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, song.getTitle());
            ps.setString(2, song.getLength());
            ps.setString(3, song.getGenre());
            ps.setInt(4, song.getAlbumId());
            ps.setInt(5, song.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update Song Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteSong(int id) {
        String query = "DELETE FROM songs WHERE id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete Song Error: " + e.getMessage());
            return false;
        }
    }
}
