package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untildawn.Enums.Weapons;

public class Weapon {
//    private Weapons weaponType;
    private final Texture smgTexture = new Texture(GameAssetManager.getGameAssetManager().getSmg());
    private Sprite smgSprite = new Sprite(smgTexture);
    private int ammo = 30;
    private float time;
    private boolean isReloading = false;
    private float reloadTimeElapsed = 0f;
    private final float RELOAD_DURATION = 2f;

    public Weapon() {
        smgSprite.setX((float) Gdx.graphics.getWidth() / 2 + 20);
        smgSprite.setY((float) Gdx.graphics.getHeight() / 2 + 40);
        smgSprite.setSize(50, 50);
    }

    public Sprite getSmgSprite() {
        return smgSprite;
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

    public float getRELOAD_DURATION() {
        return RELOAD_DURATION;
    }

    public float getReloadTimeElapsed() {
        return reloadTimeElapsed;
    }

    public void setReloadTimeElapsed(float reloadTimeElapsed) {
        this.reloadTimeElapsed = reloadTimeElapsed;
    }

}
