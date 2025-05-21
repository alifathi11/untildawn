package com.untildawn.Enums;

public enum Projectiles {
    SHOTGUN_BULLET(10, 10),
    REVOLVER_BULLET(20, 15),
    SMG_BULLET(8, 5),
    EYE_MONSTER_PROJECTILE(1, 20);
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
