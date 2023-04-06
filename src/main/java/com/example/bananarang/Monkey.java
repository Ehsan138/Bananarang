package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;

/**
 * The Monkey class is an extension of the GameObject class, representing a monkey in a game.
 * This class is responsible for handling the monkey's movements and updating its position.
 *
 * @author Ehsan Emadi & Michelle Kwok
 * @version 202304
 */
public class Monkey extends GameObject {

    private boolean moveLeft;
    private boolean moveRight;
    private final double moveSpeed = 20;

    /**
     * Constructs a new Monkey object with the specified GraphicsContext, initial position x, and y.
     *
     * @param gc the GraphicsContext used for rendering the monkey
     * @param x the initial x-coordinate of the monkey
     * @param y the initial y-coordinate of the monkey
     */
    public Monkey(final GraphicsContext gc, final double x, final double y) {
        super(gc, "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\monkey.png", x, y, 100, 100);
    }

    /**
     * Updates the monkey's position based on the moveLeft and moveRight flags.
     * Ensures that the monkey does not move outside the canvas boundaries.
     */
    public void update() {
        if (moveLeft && x > 0) {
            x -= moveSpeed;
        }
        if (moveRight && x < gc.getCanvas().getWidth() - width) {
            x += moveSpeed;
        }
    }

    /**
     * Sets the moveLeft flag, determining if the monkey should move left.
     *
     * @param moveLeft true if the monkey should move left, false otherwise
     */
    public void setMoveLeft(final boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    /**
     * Sets the moveRight flag, determining if the monkey should move right.
     *
     * @param moveRight true if the monkey should move right, false otherwise
     */
    public void setMoveRight(final boolean moveRight) {
        this.moveRight = moveRight;
    }
}
