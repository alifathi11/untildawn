package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untildawn.Enums.Weapons;

public class Weapon {
    private Weapons weaponType;
    private final Texture weaponTexture;
    private Sprite weaponSprite;

    private int totalAmmo;
    private int ammoPerMagazine;
    private int ammo;
    private int projectile;

    private float time;
    private boolean isReloading = false;
    private final float reloadDuration;
    private float reloadTimeElapsed = 0f;
    private final float shootCoolDown;
    private float timeSinceLastShot = 0f;

    private Position position;

    public Weapon(Weapons weaponType) {
        this.weaponType = weaponType;
        weaponTexture = GameAssetManager.getGameAssetManager().getWeaponTexture(weaponType);
        weaponSprite = new Sprite(weaponTexture);
        weaponSprite.setSize(50, 50);


        this.shootCoolDown = weaponType.getShootCoolDown();
        this.reloadDuration = weaponType.getTimeReload();
        this.ammoPerMagazine = weaponType.getAmmoMax();
        this.ammo = ammoPerMagazine;
        this.totalAmmo = weaponType.getTotalAmmo();
        this.projectile = weaponType.getProjectileCount();
    }

    public Sprite getWeaponSprite() {
        return weaponSprite;
    }

    public int getTotalAmmo() {
        return totalAmmo;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getAmmoPerMagazine() {
        return ammoPerMagazine;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setAmmoPerMagazine(int ammoPerMagazine) {
        this.ammoPerMagazine = ammoPerMagazine;
    }

    public void setTotalAmmo(int totalAmmo) {
        this.totalAmmo = totalAmmo;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setReloading(boolean reloading) {
        isReloading = reloading;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void reload() {

        if (ammo == ammoPerMagazine) return;

        if (!isReloading && totalAmmo > 0) {
            int deltaAmmo = Math.min(
                Math.max(totalAmmo - ammo, 0),
                Math.max(ammoPerMagazine - ammo, 0)
            );
            this.ammo += deltaAmmo;
            this.totalAmmo = Math.max(totalAmmo - deltaAmmo, 0);

            isReloading = true;
            reloadTimeElapsed = 0f;
            setTime(0f);
        }
    }

    public float getReloadDuration() {
        return reloadDuration;
    }

    public float getReloadTimeElapsed() {
        return reloadTimeElapsed;
    }

    public void setReloadTimeElapsed(float reloadTimeElapsed) {
        this.reloadTimeElapsed = reloadTimeElapsed;
    }

    public Weapons getWeaponType() {
        return weaponType;
    }

    public float getShootCoolDown() {
        return shootCoolDown;
    }

    public float getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    public void setTimeSinceLastShot(float timeSinceLastShot) {
        this.timeSinceLastShot = timeSinceLastShot;
    }

    public int getProjectile() {
        return projectile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void increaseAmmo(int deltaAmmo) {
        this.totalAmmo += deltaAmmo;
    }

}
