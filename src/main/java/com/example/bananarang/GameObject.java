package com.example.bananarang;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
    protected GraphicsContext gc;
    protected Image image;
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public GameObject(GraphicsContext gc, String imageUrl, double x, double y, double width, double height) {
        this.gc = gc;
        this.image = new Image(imageUrl);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw() {
        gc.drawImage(image, x, y, width, height);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, width, height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

}


