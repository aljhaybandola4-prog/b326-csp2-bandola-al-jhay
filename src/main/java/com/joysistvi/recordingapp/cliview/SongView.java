package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Song;
import java.util.List;
import java.util.Scanner;

public class SongView {
    private final SongController controller;
    private final Scanner scanner;

    public SongView(SongController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {
        int choice;
        do {
            System.out.println("\n----- Song Management -----");
            System.out.println("1. View All Songs");
            System.out.println("2. Search Song");
            System.out.println("3. Add Song");
            System.out.println("4. Update Song");
            System.out.println("5. Delete Song");
            System.out.println("0. Back");
            choice = readInt("Choice: ");

            switch (choice) {
                case 1 -> printSongs(controller.handleViewAllSongs());
                case 2 -> {
                    System.out.print("Search title: ");
                    printSongs(controller.searchSong(scanner.nextLine()));
                }
                case 3 -> addSong();
                case 4 -> updateSong();
                case 5 -> deleteSong();
                case 0 -> {}
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addSong() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Length (e.g. 3:59): ");
        String length = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        int albumId = readInt("Album ID: ");

        System.out.println(controller.handleCreateSong(new Song(title, length, genre, albumId))
                ? "Song added successfully." : "Failed to add song.");
    }

    private void updateSong() {
        printSongs(controller.handleViewAllSongs());
        int id = readInt("Song ID to update: ");
        Song current = controller.handleGetSongById(id);
        if (current == null) {
            System.out.println("Song not found.");
            return;
        }

        System.out.print("New title [" + current.getTitle() + "]: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) title = current.getTitle();

        System.out.print("New length [" + current.getLength() + "]: ");
        String length = scanner.nextLine().trim();
        if (length.isEmpty()) length = current.getLength();

        System.out.print("New genre [" + current.getGenre() + "]: ");
        String genre = scanner.nextLine().trim();
        if (genre.isEmpty()) genre = current.getGenre();

        int albumId = readInt("New album ID [" + current.getAlbumId() + "]: ");

        System.out.println(controller.handleUpdateSong(
                new Song(id, title, length, genre, albumId))
                ? "Song updated successfully." : "Failed to update song.");
    }

    private void deleteSong() {
        printSongs(controller.handleViewAllSongs());
        int id = readInt("Song ID to delete: ");
        System.out.println(controller.handleDeleteSong(id)
                ? "Song deleted successfully." : "Failed to delete song.");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid number."); }
        }
    }

    private void printSongs(List<Song> songs) {
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
            return;
        }
        String border = "+------+----------------------+--------+----------------------+----------+";
        System.out.println(border);
        System.out.printf("| %-4s | %-20s | %-6s | %-20s | %-8s |%n",
                "ID", "Title", "Length", "Genre", "Album ID");
        System.out.println(border);
        for (Song s : songs) {
            System.out.printf("| %-4d | %-20s | %-6s | %-20s | %-8d |%n",
                    s.getId(), s.getTitle(), s.getLength(), s.getGenre(), s.getAlbumId());
        }
        System.out.println(border);
    }
}
