package com.untildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class XP {
    private Texture xpTexture;
    private Sprite xpSprite;
    private int xp;

    private Position position;
    private CollisionRect collisionRect;

    public XP(int xp) {
        this.xpTexture = GameAssetManager.getGameAssetManager().getXpTexture();
        this.xpSprite = new Sprite(xpTexture);
        this.xpSprite.setSize(25, 25);

        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }

    public Sprite getXpSprite() {
        return xpSprite;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;

        float x = position.getX();
        float y = position.getY();

        xpSprite.setPosition(x, y);
        xpSprite.setBounds(x, y, xpSprite.getWidth(), xpSprite.getHeight());

        if (collisionRect == null) {
            collisionRect = new CollisionRect(x, y, xpSprite.getWidth(), xpSprite.getHeight());
        } else {
            collisionRect.move(x, y);
        }
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }
}
