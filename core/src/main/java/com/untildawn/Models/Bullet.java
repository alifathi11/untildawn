package com.untildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.untildawn.Enums.Bullets;

public class Bullet {
    private Texture texture;
    private Sprite sprite;

    private Vector2 position;
    private Vector2 velocity;
    private float speed;
    private float damage;

    public Bullet(Vector2 startPosition, Vector2 targetPosition, Bullets bulletType) {
        this.texture = GameAssetManager.getGameAssetManager().getBulletTexture(bulletType);
        this.sprite = new Sprite(texture);
        sprite.setSize(20, 20);

        this.speed = bulletType.getSpeed();
        this.damage = bulletType.getDamage();

        // Initialize position
        this.position = new Vector2(startPosition);
        this.sprite.setPosition(position.x, position.y);

        // Calculate direction from player to mouse
        this.velocity = new Vector2(targetPosition).sub(startPosition).nor().scl(speed);
    }

    public void update() {
        position.add(velocity);  // Move
        sprite.setPosition(position.x, position.y);
    }

    public void draw() {
        sprite.draw(com.untildawn.Main.getBatch());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDamage() {
        return damage;
    }
}
