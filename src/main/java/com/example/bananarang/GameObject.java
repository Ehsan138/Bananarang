package com.example.bananarang;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents an abstract game object that can be drawn on the game canvas.
 * This class provides methods for drawing, managing position and size,
 * and getting the bounds of the game object.
 *
 * @author Ehsan Emadi & Michelle Kwok
 * @version 202304
 */
public abstract class GameObject {
    /**
     * The GraphicsContext used to draw the game object.
     */
    protected GraphicsContext gc;

    /**
     * The image of the game object.
     */
    protected Image image;

    /**
     * The x-coordinate of the game object.
     */
    protected double x;

    /**
     * The y-coordinate of the game object.
     */
    protected double y;

    /**
     * The width of the game object.
     */
    protected double width;

    /**
     * The height of the game object.
     */
    protected double height;

    /**
     * Creates a new game object with the given properties.
     *
     * @param gc       the GraphicsContext used to draw the object
     * @param imageUrl the URL of the object's image
     * @param x        the x-coordinate of the object
     * @param y        the y-coordinate of the object
     * @param width    the width of the object
     * @param height   the height of the object
     */
    public GameObject(final GraphicsContext gc, final String imageUrl, final double x, final double y,
                      final double width, final double height) {
        this.gc = gc;
        this.image = new Image(imageUrl);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the game object on the canvas.
     */
    public void draw() {
        gc.drawImage(image, x, y, width, height);
    }

    /**
     * Returns the bounds of the game object as a Rectangle2D object.
     *
     * @return the bounds of the game object
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, width, height);
    }

    /**
     * Returns the width of the game object.
     *
     * @return the width of the game object
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the game object.
     *
     * @return the height of the game object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the x-coordinate of the game object.
     *
     * @param x the new x-coordinate
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the game object.
     *
     * @param y the new y-coordinate
     */
    public void setY(final double y) {
        this.y = y;
    }

}
