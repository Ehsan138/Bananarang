package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a Chocolate object in the game, which is a subclass of DynamicGameObject.
 * The Chocolate object moves downward at a specified speed.
 *
 * @author Ehsan Emadi & Michelle Kwok
 * @version 202304
 */
public class Chocolate extends DynamicGameObject {

    /**
     * Constructs a new Chocolate object with the specified parameters.
     *
     * @param gc     the GraphicsContext used to draw the Chocolate object
     * @param x      the x-coordinate of the Chocolate object
     * @param y      the y-coordinate of the Chocolate object
     * @param width  the width of the Chocolate object
     * @param height the height of the Chocolate object
     * @param speed  the speed at which the Chocolate object moves
     */
    public Chocolate(final GraphicsContext gc, final double x, final double y, final double width,
                     final double height, final double speed) {
        super(gc, "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\chocolate.png",
                x, y, width, height, speed);
    }

    /**
     * Updates the position of the Chocolate object, moving it downward based on its speed.
     * If the Chocolate object goes beyond the canvas's height, it reappears at the top with a random x-coordinate.
     */
    public void update() {
        y += speed;

        if (y > gc.getCanvas().getHeight()) {
            y = -height;
            x = Math.random() * (gc.getCanvas().getWidth() - width);
        }
    }
}
