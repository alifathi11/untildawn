package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.untildawn.Main;
import com.untildawn.Models.*;

public class WeaponController {
    private Weapon weapon;
    private WeaponAnimations weaponAnimations;
    private BulletController bulletController;
    private Player player;

    public WeaponController(BulletController bulletController, Weapon weapon) {
        this.bulletController = bulletController;
        this.weapon = weapon;
        this.weaponAnimations = new WeaponAnimations(weapon);

        this.player = App.getCurrentGame().getPlayer();
    }

    public void update() {
        float playerX = player.getPosition().getX();
        float playerY = player.getPosition().getY();

        weapon.setPosition(new Position(playerX + 40, playerY + 60));
        weapon.getWeaponSprite().setCenter(playerX + 20, playerY + 20);
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

    public void handleWeaponShoot(Vector2 playerPosition, Vector2 mouseWorldPosition) {
        if (weapon.getTimeSinceLastShot() >= weapon.getShootCoolDown() && weapon.getAmmo() > 0) {
            Bullet bullet = new Bullet(playerPosition, mouseWorldPosition, weapon.getWeaponType().getBullet());
            bulletController.getBullets().add(bullet);

            weapon.setAmmo(weapon.getAmmo() - 1);
            weapon.setTimeSinceLastShot(0f);
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
