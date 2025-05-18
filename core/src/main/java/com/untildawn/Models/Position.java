package com.untildawn.Models;

public class Position {
    private int X;
    private int Y;

    public Position(int Y, int X) {
        this.X = X;
        this.Y = Y;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
