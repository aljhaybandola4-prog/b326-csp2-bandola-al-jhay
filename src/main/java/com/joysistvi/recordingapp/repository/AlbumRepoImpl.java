package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Album;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepoImpl implements AlbumRepo {
    private final DbConnection dbConnection;

    public AlbumRepoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        String query = "SELECT id, name, year, artist_id FROM albums ORDER BY id";
        try (Connection conn = dbConnection.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                albums.add(new Album(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("year"), rs.getInt("artist_id")));
            }
        } catch (SQLException e) {
            System.err.println("Get All Albums Error: " + e.getMessage());
        }
        return albums;
    }

    @Override
    public Album getAlbumById(int id) {
        String query = "SELECT id, name, year, artist_id FROM albums WHERE id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Album(rs.getInt("id"), rs.getString("name"),
                            rs.getInt("year"), rs.getInt("artist_id"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Get Album By ID Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Album> searchAlbum(String keyword) {
        List<Album> albums = new ArrayList<>();
        String query = "SELECT id, name, year, artist_id FROM albums WHERE name LIKE ? ORDER BY id";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    albums.add(new Album(rs.getInt("id"), rs.getString("name"),
                            rs.getInt("year"), rs.getInt("artist_id")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Search Album Error: " + e.getMessage());
        }
        return albums;
    }

    @Override
    public boolean createAlbum(Album album) {
        String query = "INSERT INTO albums (name, year, artist_id) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, album.getName());
            ps.setInt(2, album.getYear());
            ps.setInt(3, album.getArtistId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Create Album Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateAlbum(Album album) {
        String query = "UPDATE albums SET name = ?, year = ?, artist_id = ? WHERE id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, album.getName());
            ps.setInt(2, album.getYear());
            ps.setInt(3, album.getArtistId());
            ps.setInt(4, album.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update Album Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAlbum(int id) {
        String query = "DELETE FROM albums WHERE id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete Album Error: " + e.getMessage());
            return false;
        }
    }
}
