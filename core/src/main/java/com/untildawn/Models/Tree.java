package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untildawn.Enums.Heros;

public class Tree {
    private Texture treeTexture;
    private Sprite treeSprite;
    private Position position;
    private CollisionRect collisionRect;
    private float time;

    private boolean isAnimating = true;

    public Tree() {
        this.treeTexture = GameAssetManager.getGameAssetManager().getTreeTexture();
        this.treeSprite = new Sprite(treeTexture);
        this.treeSprite.setSize(80, 100);
        treeSprite.setOrigin(0, 0);
    }

    public float getTime() {
        return time;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;

        float x = position.getX();
        float y = position.getY();

        treeSprite.setPosition(x, y);
        treeSprite.setBounds(x, y, treeSprite.getWidth(), treeSprite.getHeight());

        if (collisionRect == null) {
            collisionRect = new CollisionRect(x, y, treeSprite.getWidth(), treeSprite.getHeight());
        } else {
            collisionRect.move(x, y);
        }
    }

    public Sprite getTreeSprite() {
        return treeSprite;
    }

    public Texture getTreeTexture() {
        return treeTexture;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void setAnimating(boolean animating) {
        isAnimating = animating;
    }
}
