package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Monkey extends GameObject {

    private boolean moveLeft, moveRight;
    private Banana banana;
    private final double moveSpeed = 3;

    private Image bananaImage;

    public Monkey(GraphicsContext gc, double x, double y) {
        super(gc, "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\monkey.jpg", x, y, 100, 100);
        this.bananaImage = new Image("C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\banana.jpg");
    }

    @Override
    public void draw() {
        gc.drawImage(image, x, y, width, height);
        gc.drawImage(bananaImage, x + 50, y + 20, 30, 30); // Adjust these values to position the banana correctly
        if (banana != null) {
            banana.draw();
        }
    }

    public void update() {
        if (moveLeft && x > 0) {
            x -= moveSpeed;
        }
        if (moveRight && x < gc.getCanvas().getWidth() - width) {
            x += moveSpeed;
        }
        if (banana != null) {
            banana.update();
        }
    }

    public void throwBanana() {
        if (banana == null || !banana.isVisible()) {
            banana = new Banana(gc, x + width / 2, y);
        }
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public Banana getBanana() {
        return banana;
    }
}

