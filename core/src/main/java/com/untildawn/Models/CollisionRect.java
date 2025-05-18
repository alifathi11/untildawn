package com.untildawn.Models;

public class CollisionRect {
    private float y, x;
    private float width, height;

    public CollisionRect(float x,
                         float y,
                         float width,
                         float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(CollisionRect anotherCollisionRect) {
        return ((x <= anotherCollisionRect.x + anotherCollisionRect.width)
            && (x + width >= anotherCollisionRect.x)
            && (y >= anotherCollisionRect.y - anotherCollisionRect.height)
            && (y - height <= anotherCollisionRect.y));
    }
}
