package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a ChiliPepper object in the game, which is a subclass of DynamicGameObject.
 * The ChiliPepper object moves downward at a specified speed.
 *
 * @author Ehsan Emadi & Michelle Kwok
 * @version 202304
 */
public class ChiliPepper extends DynamicGameObject {

    /**
     * Constructs a new ChiliPepper object with the specified parameters.
     *
     * @param gc     the GraphicsContext used to draw the ChiliPepper object
     * @param x      the x-coordinate of the ChiliPepper object
     * @param y      the y-coordinate of the ChiliPepper object
     * @param width  the width of the ChiliPepper object
     * @param height the height of the ChiliPepper object
     * @param speed  the speed at which the ChiliPepper object moves
     */
    public ChiliPepper(final GraphicsContext gc, final double x, final double y, final double width,
                       final double height, final double speed) {
        super(gc, "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\chili_pepper.png",
                x, y, width, height, speed);
    }

    /**
     * Updates the position of the ChiliPepper object, moving it downward based on its speed.
     * If the ChiliPepper object goes beyond the canvas's height, it reappears at the top with a random x-coordinate.
     */
    public void update() {
        y += speed;

        if (y > gc.getCanvas().getHeight()) {
            y = -height;
            x = Math.random() * (gc.getCanvas().getWidth() - width);
        }
    }
}
