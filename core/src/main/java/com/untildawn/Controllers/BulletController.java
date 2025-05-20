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
            bullet.update();
            bullet.draw();
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    public void deleteBullet(Bullet bullet) {
        this.bullets.remove(bullet);
    }
}
