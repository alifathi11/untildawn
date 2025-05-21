package com.untildawn.Enums;

public enum Weapons {
    REVOLVER(1, 1, 6, 100, 0.5f, Projectiles.REVOLVER_BULLET),
    SMG(1, 2, 24, 500, 0.1f, Projectiles.SMG_BULLET),
    SHOTGUN(4, 1, 2, 100, 0.3f, Projectiles.SHOTGUN_BULLET),
    ;

    private final int projectileCount;
    private final int timeReload;
    private final int ammoMax;
    private final int totalAmmo;
    private final float shootCoolDown;
    private final Projectiles projectile;

    Weapons(int projectileCount,
            int timeReload,
            int ammoMax,
            int totalAmmo,
            float shootCoolDown,
            Projectiles projectile) {
        this.projectileCount = projectileCount;
        this.timeReload = timeReload;
        this.ammoMax = ammoMax;
        this.shootCoolDown = shootCoolDown;
        this.projectile = projectile;
        this.totalAmmo = totalAmmo;
    }

    public int getAmmoMax() {
        return ammoMax;
    }

    public int getTotalAmmo() {
        return totalAmmo;
    }

    public int getProjectileCount() {
        return projectileCount;
    }

    public Projectiles getProjectile() {
        return projectile;
    }

    public int getTimeReload() {
        return timeReload;
    }

    public Projectiles getBullet() {
        return projectile;
    }

    public float getShootCoolDown() {
        return shootCoolDown;
    }

}
