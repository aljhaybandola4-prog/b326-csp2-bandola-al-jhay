package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.cliview.AlbumView;
import com.joysistvi.recordingapp.cliview.ArtistView;
import com.joysistvi.recordingapp.cliview.SongView;
import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.ArtistController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.repository.AlbumRepoImpl;
import com.joysistvi.recordingapp.repository.ArtistRepoImpl;
import com.joysistvi.recordingapp.repository.SongRepoImpl;
import com.joysistvi.recordingapp.service.AlbumServiceImpl;
import com.joysistvi.recordingapp.service.ArtistServiceImpl;
import com.joysistvi.recordingapp.service.SongServiceImpl;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DbConnection dbConnection = new DbConnection();

        // Artist
        ArtistController artistController = new ArtistController(
                new ArtistServiceImpl(
                        new ArtistRepoImpl(dbConnection)
                )
        );

        // Album
        AlbumController albumController = new AlbumController(
                new AlbumServiceImpl(
                        new AlbumRepoImpl(dbConnection)
                )
        );

        // Song
        SongController songController = new SongController(
                new SongServiceImpl(
                        new SongRepoImpl(dbConnection)
                )
        );

        // Views
        ArtistView artistView = new ArtistView(artistController, scanner);
        AlbumView albumView = new AlbumView(albumController, scanner);
        SongView songView = new SongView(songController, scanner);

        int choice;

        do {
            System.out.println("\n========== RECORDING STUDIO APP ==========");
            System.out.println("1. Artist Management");
            System.out.println("2. Album Management");
            System.out.println("3. Song Management");
            System.out.println("0. Exit");

            choice = readInt(scanner, "Choice: ");

            switch (choice) {

                case 1:
                    artistView.run();
                    break;

                case 2:
                    albumView.run();
                    break;

                case 3:
                    songView.run();
                    break;

                case 0:
                    System.out.println("Thank you for using the Recording Studio App.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static int readInt(Scanner scanner, String prompt) {

        while (true) {

            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
