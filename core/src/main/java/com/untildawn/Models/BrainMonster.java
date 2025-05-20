package com.untildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BrainMonster extends Monster {
    private Texture texture;
    private Sprite sprite;
    private Position position;

    private float speed;
    private float HP;
    private int killXP;

    private float time;

    private CollisionRect collisionRect;

    public BrainMonster(float HP, float speed, int killXP) {
        super(HP, speed, killXP);
        this.texture = GameAssetManager.getGameAssetManager().getBrainMonsterTexture();
        this.sprite = new Sprite(texture);
        this.sprite.setSize(50, 50);
        this.collisionRect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void setPosition(Position position) {
        this.position = position;
        this.sprite.setPosition(position.getX(), position.getY());
        this.collisionRect.move(position.getX(), position.getY());
    }

    public Position getPosition() {
        return position;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public float getHP() {
        return HP;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
