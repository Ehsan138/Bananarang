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

    private GraphicsContext gc;
    private ArrayList<GameObject> gameObjects;
    private Monkey monkey;
    private boolean gamePaused;
    private int score;
    private int chiliCounter;
    @FXML
    private Button playButton;
    private AtomicInteger speedIncreaseCounter = new AtomicInteger(0);
    private AnimationTimer gameLoop;
    private Timeline speedIncreaseTimeline;
    @FXML
    private ImageView backgroundImageView;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        backgroundImageView.setImage(new Image("C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\jungle.jpg"));

        Font jungleLandFont = Font.loadFont(getClass().getResourceAsStream("/Jungle Land.ttf"), 18);
        Font jungleLandFont48 = Font.loadFont(getClass().getResourceAsStream("/Jungle Land.ttf"), 48);

        instructionLabel.setFont(jungleLandFont);
        instructionLabel.setVisible(true);

        scoreLabel.setFont(jungleLandFont);
        chiliCounterLabel.setFont(jungleLandFont);
        gameOverLabel.setFont(jungleLandFont48);
        playButton.setFont(jungleLandFont);
        pauseResumeButton.setFont(jungleLandFont);

        gc = gameCanvas.getGraphicsContext2D();
        gameObjects = new ArrayList<>();
        monkey = new Monkey(gc, gameCanvas.getWidth() / 2, gameCanvas.getHeight() - 100);
        gameObjects.add(monkey);
        gamePaused = true;

        // Add chocolates and chili peppers to gameObjects
        for (int i = 0; i < 5; i++) {
            double x = Math.random() * (gameCanvas.getWidth() - 50);
            double y = Math.random() * (gameCanvas.getHeight() / 2);
            double speed = 1 + Math.random() * 2;
            gameObjects.add(new Chocolate(gc, x, y, 50, 50, speed));
        }

        for (int i = 0; i < 3; i++) {
            double x = Math.random() * (gameCanvas.getWidth() - 50);
            double y = Math.random() * (gameCanvas.getHeight() / 2);
            double speed = 2 + Math.random() * 5; // Increase the initial speed here
            gameObjects.add(new ChiliPepper(gc, x, y, 50, 50, speed));
        }

        score = 0;
        chiliCounter = 3;
        updateUI();

        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(this::handleKeyPressed);
        gameCanvas.setOnKeyReleased(this::handleKeyReleased);

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gamePaused) {
                    updateGame();
                    renderGame();
                }
            }
        };

        gameLoop.start();
        speedIncreaseTimeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
            speedIncreaseCounter.incrementAndGet();
            gameObjects.stream()
                    .filter(obj -> obj instanceof Chocolate || obj instanceof ChiliPepper)
                    .forEach(obj -> ((DynamicGameObject) obj).speed += (obj instanceof ChiliPepper ? 0.5 : 0.3));
        }));
        speedIncreaseTimeline.setCycleCount(Timeline.INDEFINITE);
        speedIncreaseTimeline.play();

        initBackgroundMusic();
    }

    @FXML
    private void startGame() {
        backgroundImageView.setVisible(false);
        if (gamePaused) {
            if (chiliCounter == 0) {
                // Reset game state
                score = 0;
                chiliCounter = 3;
                speedIncreaseCounter.set(0);
                updateUI();
                gameObjects.stream()
                        .filter(obj -> obj instanceof Chocolate || obj instanceof ChiliPepper)
                        .forEach(obj -> ((DynamicGameObject) obj).speed = (obj instanceof Chocolate) ? 1 + Math.random() * 2 : 2 + Math.random() * 5);
            }
            gamePaused = false;
            instructionLabel.setVisible(false);
            gameCanvas.requestFocus();
            gameOverLabel.setVisible(false);
            gameCanvas.requestFocus();
            gameLoop.start();
            speedIncreaseTimeline.play();
        }
        mediaPlayer.play();
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                monkey.setMoveLeft(true);
                break;
            case D:
                monkey.setMoveRight(true);
                break;
        }
    }

    private void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                monkey.setMoveLeft(false);
                break;
            case D:
                monkey.setMoveRight(false);
                break;
        }
    }

    private void updateGame() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Chocolate || gameObject instanceof ChiliPepper) {
                ((DynamicGameObject) gameObject).update();
            }
        }
        monkey.update();

        List<GameObject> collidedObjects = gameObjects.stream()
                .filter(obj -> obj.getBounds().intersects(monkey.getBounds()))
                .collect(Collectors.toList());

        for (GameObject obj : collidedObjects) {
            if (obj instanceof Chocolate) {
                score++;
                resetObjectPosition(obj);
            } else if (obj instanceof ChiliPepper) {
                chiliCounter--;
                if (chiliCounter == 0) {
                    // Game over logic
                    gamePaused = true;
                    showGameOver();
                }
                resetObjectPosition(obj);
            }
        }

        updateUI();
    }

    private void renderGame() {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        Image backgroundImage = new Image("C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\jungle.jpg");
        gc.drawImage(backgroundImage, 0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        for (GameObject gameObject : gameObjects) {
            gameObject.draw();
        }
    }

    private void resetObjectPosition(GameObject obj) {
        obj.setX(Math.random() * (gameCanvas.getWidth() - obj.getWidth()));
        obj.setY(-obj.getHeight());

        if (obj instanceof DynamicGameObject) {
            ((DynamicGameObject) obj).speed += speedIncreaseCounter.get() * 0.1;
        }
    }

    private void updateUI() {
        scoreLabel.setText(String.valueOf(score));
        chiliCounterLabel.setText(String.valueOf(chiliCounter));
    }

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

    private void showGameOver() {
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 48));
        gc.setFill(Color.RED);
        gc.fillText("GAME OVER", gameCanvas.getWidth() / 2 - 120, gameCanvas.getHeight() / 2);
        playButton.setDisable(false);
        playButton.setText("Restart");

        playButton.setText("Restart");
        gameOverLabel.setVisible(true);
        gameLoop.stop();
        speedIncreaseTimeline.stop();

        playButton.setOnAction(event -> {
            restartMusic();
            startGame();
        });
    }

    private void initBackgroundMusic() {
        String musicFile = "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\In-the-Past.mp3";
        Media media = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private void restartMusic() {
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();
    }
}

