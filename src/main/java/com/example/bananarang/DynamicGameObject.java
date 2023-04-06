package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a dynamic game object that moves during gameplay.
 * This class is an extension of the GameObject class and adds a speed attribute.
 *
 * @author Ehsan Emadi & Michelle Kwok
 * @version 202304
 */
public abstract class DynamicGameObject extends GameObject {
    /**
     * The speed at which the game object moves.
     */
    protected double speed;

    /**
     * Constructs a new DynamicGameObject with the specified parameters.
     *
     * @param gc        the GraphicsContext used to draw the game object
     * @param imageUrl  the URL of the image representing the game object
     * @param x         the x-coordinate of the game object
     * @param y         the y-coordinate of the game object
     * @param width     the width of the game object
     * @param height    the height of the game object
     * @param speed     the speed at which the game object moves
     */
    public DynamicGameObject(final GraphicsContext gc, final String imageUrl, final double x, final double y,
                             final double width, final double height, final double speed) {
        super(gc, imageUrl, x, y, width, height);
        this.speed = speed;
    }

    /**
     * Updates the state of the game object, typically involving changes in position or other dynamic attributes.
     */
    public abstract void update();
}
