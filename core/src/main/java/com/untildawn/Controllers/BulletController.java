package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.untildawn.Main;
import com.untildawn.Models.Bullet;

import java.util.ArrayList;

public class BulletController {
    private ArrayList<Bullet> bullets;

    public BulletController() {
        this.bullets = new ArrayList<>();
    }

    public void update() {
        for (Bullet bullet : bullets) {
            bullet.getSprite().draw(Main.getBatch());

            Vector2 direction = new Vector2(
                Gdx.graphics.getWidth() / 2f - bullet.getX(),
                Gdx.graphics.getHeight() / 2f - bullet.getY()
            ).nor();

            bullet.getSprite().setX(bullet.getSprite().getX() - direction.x * 5);
            bullet.getSprite().setY(bullet.getSprite().getY() + direction.y * 5);
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
