package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untildawn.Enums.Weapons;

public class Weapon {
    private Weapons weaponType;
    private final Texture weaponTexture;
    private Sprite weaponSprite;
    private int ammo = 30;
    private float time;
    private boolean isReloading = false;
    private float reloadTimeElapsed = 0f;
    private final float reloadDuration = 2f;
    private final float shootCoolDown = 0.5f;
    private float timeSinceLastShot = 0f;

    public Weapon(Weapons weaponType) {
        this.weaponType = weaponType;
        weaponTexture = GameAssetManager.getGameAssetManager().getWeaponTexture(weaponType);
        weaponSprite = new Sprite(weaponTexture);
        weaponSprite.setX((float) Gdx.graphics.getWidth() / 2 + 20);
        weaponSprite.setY((float) Gdx.graphics.getHeight() / 2 + 40);
        weaponSprite.setSize(50, 50);
    }

    public Sprite getWeaponSprite() {
        return weaponSprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
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
        if (!isReloading) {
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
}
