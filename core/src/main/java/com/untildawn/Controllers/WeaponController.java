package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.untildawn.Enums.Weapons;
import com.untildawn.Main;
import com.untildawn.Models.Bullet;
import com.untildawn.Models.Weapon;

import java.util.ArrayList;

public class WeaponController {
    private Weapon weapon;
    private BulletController bulletController;

    public WeaponController(BulletController bulletController, Weapon weapon) {
        this.bulletController = bulletController;
        this.weapon = weapon;
    }

    public void update() {
        weapon.getSmgSprite().draw(Main.getBatch());
    }

    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getSmgSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (Math.PI - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int x, int y) {
        bulletController.getBullets().add(new Bullet(x, y));
        weapon.setAmmo(weapon.getAmmo() - 1);
    }
}
