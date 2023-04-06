package com.example.bananarang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class is an entry point for the Bananarang game.
 * This class extends Application and sets up the primary stage for the JavaFX application.
 *
 * @author Ehsan Emadi & Michelle Kwok
 * @version 202304
 */
public class Main extends Application {

    /**
     * Sets up and shows the primary stage for the JavaFX application.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set
     * @throws Exception if there is an issue loading the game view FXML file
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gameView.fxml"));
        primaryStage.setTitle("Bananarang");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The main method for launching the Bananarang application.
     *
     * @param args the command-line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * Overrides the stop() method from the Application class to ensure the application
     * exits cleanly when the primary stage is closed.
     */
    @Override
    public void stop() {
        System.exit(0);
    }
}
