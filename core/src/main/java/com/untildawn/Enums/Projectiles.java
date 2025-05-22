package com.untildawn.Enums;

public enum Projectiles {
    SHOTGUN_BULLET(10, 15),
    REVOLVER_BULLET(20, 20),
    SMG_BULLET(8, 10),
    EYE_MONSTER_PROJECTILE(1, 15);
    ;

    private final float damage;
    private final float speed;

    Projectiles(float damage, float speed) {
        this.damage = damage;
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDamage() {
        return damage;
    }
}
