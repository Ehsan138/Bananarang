package com.example.bananarang;

import javafx.scene.canvas.GraphicsContext;

public class Banana extends GameObject {

    private boolean visible;
    private double angle;
    private double centerX, centerY;
    private final double radius = 100;
    private final double speed = 0.1;

    public Banana(GraphicsContext gc, double x, double y) {
        super(gc, "C:\\Users\\ehsan\\IdeaProjects\\Bananarang\\src\\main\\resources\\banana.jpg", x, y, 50, 50);
        this.centerX = x;
        this.centerY = y - radius;
        this.angle = 0;
        this.visible = true;
    }

    public void update() {
        if (visible) {
            angle += speed;
            x = centerX + Math.cos(angle) * radius;
            y = centerY + Math.sin(angle) * radius;

            if (angle >= Math.PI * 2) {
                visible = false;
            }
        }
    }

    @Override
    public void draw() {
        if (visible) {
            super.draw();
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}

