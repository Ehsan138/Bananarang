package com.example.bananarang;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * GameViewController is a controller class for the Bananarang game. It initializes
 * and manages the game's user interface, game objects, and game loop.
 *
 * @author Ehsan Emadi & Michelle Kwok
 * @version 202304
 */
public class GameViewController implements Initializable {

    @FXML
    private Canvas gameCanvas;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label chiliCounterLabel;
    @FXML
    private Button pauseResumeButton;
    @FXML
    private Label gameOverLabel;
    @FXML
    private Label instructionLabel;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private Button playButton;

    private GraphicsContext gc;
    private ArrayList<GameObject> gameObjects;
    private Monkey monkey;
    private boolean gamePaused;
    private int score;
    private int chiliCounter;
    private final AtomicInteger speedIncreaseCounter = new AtomicInteger(0);
    private AnimationTimer gameLoop;
    private Timeline speedIncreaseTimeline;
    private MediaPlayer mediaPlayer;

    /**
     * Initializes the controller class, sets up the game, UI components, and event handlers.
     *
     * @param location  the location used to resolve relative paths for the root object
     * @param resources the resources used to localize the root object
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // Set background image
        backgroundImageView.setImage(new Image(
                "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\jungle.jpg"));

        // Load fonts
        Font jungleLandFont = Font.loadFont(getClass().getResourceAsStream("/Jungle Land.ttf"), 18);
        Font jungleLandFont48 = Font.loadFont(getClass().getResourceAsStream("/Jungle Land.ttf"), 48);

        // Set fonts and visibility for UI elements
        instructionLabel.setFont(jungleLandFont);
        instructionLabel.setVisible(true);
        scoreLabel.setFont(jungleLandFont);
        chiliCounterLabel.setFont(jungleLandFont);
        gameOverLabel.setFont(jungleLandFont48);
        playButton.setFont(jungleLandFont);
        pauseResumeButton.setFont(jungleLandFont);

        // Initialize game objects
        gc = gameCanvas.getGraphicsContext2D();
        gameObjects = new ArrayList<>();
        monkey = new Monkey(gc, gameCanvas.getWidth() / 2, gameCanvas.getHeight() - 100);
        gameObjects.add(monkey);
        gamePaused = true;

        // Add chocolates to gameObjects
        for (int i = 0; i < 5; i++) {
            double x = Math.random() * (gameCanvas.getWidth() - 50);
            double y = Math.random() * (gameCanvas.getHeight() / 2);
            double speed = 1 + Math.random() * 2;
            gameObjects.add(new Chocolate(gc, x, y, 50, 50, speed));
        }

        // Add chili peppers to gameObjects
        for (int i = 0; i < 3; i++) {
            double x = Math.random() * (gameCanvas.getWidth() - 50);
            double y = Math.random() * (gameCanvas.getHeight() / 2);
            double speed = 2 + Math.random() * 5; // Increase the initial speed here
            gameObjects.add(new ChiliPepper(gc, x, y, 50, 50, speed));
        }

        // Initialize game state
        score = 0;
        chiliCounter = 3;
        updateUI();

        // Set key event handlers
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(this::handleKeyPressed);
        gameCanvas.setOnKeyReleased(this::handleKeyReleased);

        // Initialize game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (!gamePaused) {
                    updateGame();
                    renderGame();
                }
            }
        };

        // Start game loop and speed increase timeline
        gameLoop.start();
        speedIncreaseTimeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
            speedIncreaseCounter.incrementAndGet();
            gameObjects.stream()
                    .filter(obj -> obj instanceof Chocolate || obj instanceof ChiliPepper)
                    .forEach(obj -> ((DynamicGameObject) obj).speed += (obj instanceof ChiliPepper ? 0.5 : 0.3));
        }));
        speedIncreaseTimeline.setCycleCount(Timeline.INDEFINITE);
        speedIncreaseTimeline.play();

        // Initialize background music
        initBackgroundMusic();
    }

    /**
     * Starts the game by hiding the background image and starting the game loop.
     */
    @FXML
    private void startGame() {
        // Hide background image
        backgroundImageView.setVisible(false);

        // Start game if it is paused
        if (gamePaused) {
            // Reset game state if no chili peppers are left
            if (chiliCounter == 0) {
                score = 0;
                chiliCounter = 3;
                speedIncreaseCounter.set(0);
                updateUI();
                gameObjects.stream()
                        .filter(obj -> obj instanceof Chocolate || obj instanceof ChiliPepper)
                        .forEach(obj -> ((DynamicGameObject) obj).speed = (obj instanceof Chocolate) ? 1
                                + Math.random() * 2 : 2 + Math.random() * 5);
            }

            // Unpause game and hide instructions and game over labels
            gamePaused = false;
            instructionLabel.setVisible(false);
            gameCanvas.requestFocus();
            gameOverLabel.setVisible(false);

            // Request focus on game canvas and start game loop and speed increase timeline
            gameCanvas.requestFocus();
            gameLoop.start();
            speedIncreaseTimeline.play();
        }

        // Play background music
        mediaPlayer.play();
    }

    /**
     * Handles key press events for the monkey's movement.
     *
     * @param event the KeyEvent triggered by a key press
     */
    private void handleKeyPressed(final KeyEvent event) {
        switch (event.getCode()) {
            case A:
                monkey.setMoveLeft(true);
                break;
            case D:
                monkey.setMoveRight(true);
                break;
        }
    }

    /**
     * Handles key release events for the monkey's movement.
     *
     * @param event the KeyEvent triggered by a key release
     */
    private void handleKeyReleased(final KeyEvent event) {
        switch (event.getCode()) {
            case A:
                monkey.setMoveLeft(false);
                break;
            case D:
                monkey.setMoveRight(false);
                break;
        }
    }

    /**
     * Updates the game state, including the position of game objects and checking for collisions.
     */
    private void updateGame() {
        // Update position of chocolates and chili peppers
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Chocolate || gameObject instanceof ChiliPepper) {
                ((DynamicGameObject) gameObject).update();
            }
        }

        // Update monkey's position
        monkey.update();

        // Check for collisions between monkey and game objects
        List<GameObject> collidedObjects = gameObjects.stream()
                .filter(obj -> obj.getBounds().intersects(monkey.getBounds()))
                .collect(Collectors.toList());

        // Handle collisions
        for (GameObject obj : collidedObjects) {
            if (obj instanceof Chocolate) {
                // Increase score for chocolate
                score++;
                resetObjectPosition(obj);
            } else if (obj instanceof ChiliPepper) {
                // Decrease chili counter
                chiliCounter--;

                // Check for game over
                if (chiliCounter == 0) {
                    // Game over logic
                    gamePaused = true;
                    showGameOver();
                }
                resetObjectPosition(obj);
            }
        }

        // Update user interface
        updateUI();
    }

    /**
     * Renders the game by drawing the game objects on the canvas.
     */
    private void renderGame() {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        Image backgroundImage = new Image(
                "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\jungle.jpg");
        gc.drawImage(backgroundImage, 0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        for (GameObject gameObject : gameObjects) {
            gameObject.draw();
        }
    }

    /**
     * Resets the position and speed of a game object.
     *
     * @param obj the GameObject to reset
     */
    private void resetObjectPosition(final GameObject obj) {
        obj.setX(Math.random() * (gameCanvas.getWidth() - obj.getWidth()));
        obj.setY(-obj.getHeight());

        if (obj instanceof DynamicGameObject) {
            ((DynamicGameObject) obj).speed += speedIncreaseCounter.get() * 0.1;
        }
    }

    /**
     * Updates the user interface elements, such as the score and chili counter.
     */
    private void updateUI() {
        scoreLabel.setText(String.valueOf(score));
        chiliCounterLabel.setText(String.valueOf(chiliCounter));
    }

    /**
     * Pauses or resumes the game, toggling the game state and updating the pause/resume button text.
     */
    @FXML
    private void pauseResumeGame() {
        gamePaused = !gamePaused;
        pauseResumeButton.setText(gamePaused ? "Resume" : "Pause");
        if (!gamePaused) {
            mediaPlayer.play();
            gameCanvas.requestFocus();
        } else {
            mediaPlayer.pause();
        }
    }

    /**
     * Displays the "Game Over" message and updates the play button to restart the game.
     */
    private void showGameOver() {
        // Set font and color for "GAME OVER" text
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 48));
        gc.setFill(Color.RED);

        // Draw "GAME OVER" text on canvas
        gc.fillText("GAME OVER", gameCanvas.getWidth() / 2 - 120, gameCanvas.getHeight() / 2);

        // Enable and set text for play button
        playButton.setDisable(false);
        playButton.setText("Restart");

        // Show game over label
        gameOverLabel.setVisible(true);

        // Stop game loop and speed increase timeline
        gameLoop.stop();
        speedIncreaseTimeline.stop();

        // Set action for play button to restart music and start game
        playButton.setOnAction(event -> {
            restartMusic();
            startGame();
        });
    }

    /**
     * Initializes the background music for the game.
     */
    private void initBackgroundMusic() {
        String musicFile = "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\In-the-Past.mp3";
        Media media = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * Restarts the background music, stopping the current playback and starting from the beginning.
     */
    private void restartMusic() {
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();
    }
}
