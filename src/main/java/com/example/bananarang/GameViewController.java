package com.example.bananarang;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class GameViewController implements Initializable {

    @FXML
    private Canvas gameCanvas;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label chiliCounterLabel;
    @FXML
    private Button pauseResumeButton;

    private GraphicsContext gc;
    private ArrayList<GameObject> gameObjects;
    private Monkey monkey;
    private boolean gamePaused;
    private int score;
    private int chiliCounter;
    @FXML
    private Button playButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = gameCanvas.getGraphicsContext2D();
        gameObjects = new ArrayList<>();
        monkey = new Monkey(gc, gameCanvas.getWidth() / 2, gameCanvas.getHeight() - 100);
        gameObjects.add(monkey);
        gamePaused = true;

        // Add chocolates and chili peppers to gameObjects here
        for (int i = 0; i < 5; i++) {
            double x = Math.random() * (gameCanvas.getWidth() - 50);
            double y = Math.random() * (gameCanvas.getHeight() / 2);
            double speed = 1 + Math.random() * 2;
            gameObjects.add(new Chocolate(gc, x, y, 50, 50, speed));
        }

        for (int i = 0; i < 3; i++) {
            double x = Math.random() * (gameCanvas.getWidth() - 50);
            double y = Math.random() * (gameCanvas.getHeight() / 2);
            double speed = 1 + Math.random() * 2;
            gameObjects.add(new ChiliPepper(gc, x, y, 50, 50, speed));
        }

        gamePaused = false;
        score = 0;
        chiliCounter = 3;
        updateUI();

        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(this::handleKeyPressed);
        gameCanvas.setOnKeyReleased(this::handleKeyReleased);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gamePaused) {
                    updateGame();
                    renderGame();
                }
            }
        };
    }

    @FXML
    private void startGame() {
        gamePaused = false;
        gameCanvas.requestFocus();
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gamePaused) {
                    updateGame();
                    renderGame();
                }
            }
        };
        gameLoop.start();
        playButton.setDisable(true);
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                monkey.setMoveLeft(true);
                break;
            case D:
                monkey.setMoveRight(true);
                break;
            case SPACE:
                monkey.throwBanana();
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

        if (monkey.getBanana() != null && monkey.getBanana().isVisible()) {
            List<GameObject> collidedObjects = gameObjects.stream()
                    .filter(obj -> obj.getBounds().intersects(monkey.getBanana().getBounds()))
                    .collect(Collectors.toList());

            for (GameObject obj : collidedObjects) {
                if (obj                 instanceof Chocolate) {
                    score++;
                    resetObjectPosition(obj);
                } else if (obj instanceof ChiliPepper) {
                    chiliCounter--;
                    if (chiliCounter == 0) {
                        // Game over logic
                        gamePaused = true;
                        pauseResumeButton.setDisable(true);
                    }
                    resetObjectPosition(obj);
                }
            }

            monkey.getBanana().setVisible(false);
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
            gameCanvas.requestFocus();
        }
    }

}

