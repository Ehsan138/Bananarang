package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;

public class Chocolate extends DynamicGameObject {

    public Chocolate(GraphicsContext gc, double x, double y, double width, double height, double speed) {
        super(gc, "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\chocolate.png", x, y, width, height, speed);
    }

    public void update() {
        y += speed;

        if (y > gc.getCanvas().getHeight()) {
            y = -height;
            x = Math.random() * (gc.getCanvas().getWidth() - width);
        }
    }
}

