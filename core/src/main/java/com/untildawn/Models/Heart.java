package com.untildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Heart {
    private Texture heartTexture;
    private Sprite heartSprite;

    private Position position;
    private CollisionRect collisionRect;

    public Heart() {
        this.heartTexture = GameAssetManager.getGameAssetManager().getHeartPickUpTexture();
        this.heartSprite = new Sprite(heartTexture);
        this.heartSprite.setSize(25, 25);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;

        float x = position.getX();
        float y = position.getY();

        heartSprite.setPosition(x, y);
        heartSprite.setBounds(x, y, heartSprite.getWidth(), heartTexture.getHeight());

        if (collisionRect == null) {
            collisionRect = new CollisionRect(x, y, heartTexture.getWidth(), heartSprite.getHeight());
        } else {
            collisionRect.move(x, y);
        }
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public Sprite getHeartSprite() {
        return heartSprite;
    }
}
