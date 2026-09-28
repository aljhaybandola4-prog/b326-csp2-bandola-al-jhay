package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.model.Album;
import java.util.List;
import java.util.Scanner;

public class AlbumView {
    private final AlbumController controller;
    private final Scanner scanner;

    public AlbumView(AlbumController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void run() {
        int choice;
        do {
            System.out.println("\n----- Album Management -----");
            System.out.println("1. View All Albums");
            System.out.println("2. Search Album");
            System.out.println("3. Add Album");
            System.out.println("4. Update Album");
            System.out.println("5. Delete Album");
            System.out.println("0. Back");
            choice = readInt("Choice: ");

            switch (choice) {
                case 1 -> printAlbums(controller.handleViewAllAlbums());
                case 2 -> {
                    System.out.print("Search album: ");
                    printAlbums(controller.searchAlbum(scanner.nextLine()));
                }
                case 3 -> addAlbum();
                case 4 -> updateAlbum();
                case 5 -> deleteAlbum();
                case 0 -> {}
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addAlbum() {
        System.out.print("Album name: ");
        String name = scanner.nextLine();
        int year = readInt("Year: ");
        int artistId = readInt("Artist ID: ");
        boolean success = controller.handleCreateAlbum(new Album(name, year, artistId));
        System.out.println(success ? "Album added successfully." : "Failed to add album.");
    }

    private void updateAlbum() {
        printAlbums(controller.handleViewAllAlbums());
        int id = readInt("Album ID to update: ");
        Album current = controller.handleGetAlbumById(id);
        if (current == null) {
            System.out.println("Album not found.");
            return;
        }

        System.out.print("New name [" + current.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = current.getName();

        int year = readInt("New year [" + current.getYear() + "]: ");
        int artistId = readInt("New artist ID [" + current.getArtistId() + "]: ");

        boolean success = controller.handleUpdateAlbum(
                new Album(id, name, year, artistId));
        System.out.println(success ? "Album updated successfully." : "Failed to update album.");
    }

    private void deleteAlbum() {
        printAlbums(controller.handleViewAllAlbums());
        int id = readInt("Album ID to delete: ");
        System.out.println(controller.handleDeleteAlbum(id)
                ? "Album deleted successfully." : "Failed to delete album.");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid number."); }
        }
    }

    private void printAlbums(List<Album> albums) {
        if (albums.isEmpty()) {
            System.out.println("No albums found.");
            return;
        }
        String border = "+------+----------------------+--------+----------+";
        System.out.println(border);
        System.out.printf("| %-4s | %-20s | %-6s | %-8s |%n", "ID", "Name", "Year", "Artist ID");
        System.out.println(border);
        for (Album a : albums) {
            System.out.printf("| %-4d | %-20s | %-6d | %-8d |%n",
                    a.getId(), a.getName(), a.getYear(), a.getArtistId());
        }
        System.out.println(border);
    }
}
