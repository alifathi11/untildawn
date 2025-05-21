package com.untildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untildawn.Enums.Projectiles;
import com.untildawn.Enums.Weapons;

public class Ammo {
    private Texture ammoTexture;
    private Sprite ammoSprite;
    private Weapons weaponType;
    private Projectiles bulletType;

    private Position position;
    private CollisionRect collisionRect;

    private int ammoAmount;


    public Ammo(int ammo, Weapons weaponType) {
        this.ammoTexture = GameAssetManager.getGameAssetManager().getAmmoTexture();
        this.ammoSprite = new Sprite(ammoTexture);

        this.ammoAmount = ammo;
        this.weaponType = weaponType;
        this.bulletType = weaponType.getBullet();
    }

    public void setPosition(Position position) {
        this.position = position;

        float x = position.getX();
        float y = position.getY();

        ammoSprite.setPosition(x, y);
        ammoSprite.setBounds(x, y, ammoSprite.getWidth(), ammoSprite.getHeight());

        if (collisionRect == null) {
            collisionRect = new CollisionRect(x, y, ammoSprite.getWidth(), ammoSprite.getHeight());
        } else {
            collisionRect.move(x, y);
        }
    }

    public Position getPosition() {
        return position;
    }

    public Sprite getAmmoSprite() {
        return ammoSprite;
    }

    public int getAmmoAmount() {
        return ammoAmount;
    }

    public Projectiles getBulletType() {
        return bulletType;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public Weapons getWeaponType() {
        return weaponType;
    }
}
