package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;

public class Monkey extends GameObject {

    private boolean moveLeft, moveRight;
    private final double moveSpeed = 20;

    public Monkey(GraphicsContext gc, double x, double y) {
        super(gc, "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\monkey.png", x, y, 100, 100);
    }

    public void update() {
        if (moveLeft && x > 0) {
            x -= moveSpeed;
        }
        if (moveRight && x < gc.getCanvas().getWidth() - width) {
            x += moveSpeed;
        }
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
}
