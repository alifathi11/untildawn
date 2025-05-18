package com.untildawn.Enums;

public enum Weapons {
    REVOLVER(20, 1, 1, 6),
    SHOTGUN(10, 4, 1, 2),
    SMGsDUAL(8, 1, 2, 24),
    ;

    private final int damage;
    private final int projectile;
    private final int timeReload;
    private final int ammoMax;

    Weapons(int damage,
            int projectile,
            int timeReload,
            int ammoMax) {
        this.damage = damage;
        this.projectile = projectile;
        this.timeReload = timeReload;
        this.ammoMax = ammoMax;
    }

    public int getAmmoMax() {
        return ammoMax;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectile() {
        return projectile;
    }

    public int getTimeReload() {
        return timeReload;
    }
}
