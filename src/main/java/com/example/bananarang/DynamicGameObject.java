package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;

public abstract class DynamicGameObject extends GameObject {
    protected double speed;

    public DynamicGameObject(GraphicsContext gc, String imageUrl, double x, double y, double width, double height, double speed) {
        super(gc, imageUrl, x, y, width, height);
        this.speed = speed;
    }

    public abstract void update();
}

