package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.untildawn.Main;
import com.untildawn.Models.Bullet;
import com.untildawn.Models.Weapon;

public class WeaponController {
    private Weapon weapon;
    private WeaponAnimations weaponAnimations;
    private BulletController bulletController;

    public WeaponController(BulletController bulletController, Weapon weapon) {
        this.bulletController = bulletController;
        this.weapon = weapon;
        this.weaponAnimations = new WeaponAnimations(weapon);
    }

    public void update() {
        weapon.getWeaponSprite().draw(Main.getBatch());
        if (weapon.isReloading()) {
            weaponAnimations.reloadAnimation();
        }
        weapon.setTimeSinceLastShot(weapon.getTimeSinceLastShot() + Gdx.graphics.getDeltaTime());
    }

    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getWeaponSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (Math.PI - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int x, int y) {
        if (weapon.getTimeSinceLastShot() >= weapon.getShootCoolDown() && weapon.getAmmo() > 0) {
            bulletController.getBullets().add(new Bullet(x, y));
            weapon.setAmmo(weapon.getAmmo() - 1);
            weapon.setTimeSinceLastShot(0f);
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
