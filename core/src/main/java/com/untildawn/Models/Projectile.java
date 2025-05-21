package com.untildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.untildawn.Enums.Projectiles;

public class Projectile {
    private Texture texture;
    private Sprite sprite;

    private Vector2 position;
    private Vector2 velocity;
    private float speed;
    private float damage;

    private CollisionRect collisionRect;

    private Projectiles projectileType;

    public Projectile(Vector2 startPosition, Vector2 direction, Projectiles projectileType) {
        this.texture = GameAssetManager.getGameAssetManager().getProjectileTexture(projectileType);
        this.sprite = new Sprite(texture);
        sprite.setSize(20, 20);

        this.projectileType = projectileType;

        this.speed = projectileType.getSpeed();
        this.damage = projectileType.getDamage();

        this.position = new Vector2(startPosition);
        this.sprite.setPosition(position.x, position.y);

        this.velocity = new Vector2(direction).nor().scl(speed);

        this.collisionRect = new CollisionRect(position.x, position.y, sprite.getWidth(), sprite.getHeight());
    }

    public void update() {
        position.add(velocity);
        sprite.setPosition(position.x, position.y);
        collisionRect.move(position.x, position.y);
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

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public Projectiles getProjectileType() {
        return projectileType;
    }
}
