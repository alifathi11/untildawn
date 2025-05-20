package com.untildawn.Models;

public class Position {
    private float X;
    private float Y;

    public Position(float X, float Y) {
        this.X = X;
        this.Y = Y;
    }

    public void setX(float x) {
        X = x;
    }

    public void setY(float y) {
        Y = y;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }
}
