package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;

public class ChiliPepper extends DynamicGameObject {

    public ChiliPepper(GraphicsContext gc, double x, double y, double width, double height, double speed) {
        super(gc, "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\chili_pepper.png", x, y, width, height, speed);
    }

    public void update() {
        y += speed;

        if (y > gc.getCanvas().getHeight()) {
            y = -height;
            x = Math.random() * (gc.getCanvas().getWidth() - width);
        }
    }
}

