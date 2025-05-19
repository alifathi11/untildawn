package com.untildawn.Enums;

public enum Weapons {
    REVOLVER(1, 1, 6, 100, 0.5f, Bullets.REVOLVER_BULLET),
    SHOTGUN(4, 1, 2, 100, 0.3f, Bullets.SHOTGUN_BULLET),
    SMG(1, 2, 24, 500, 0.1f, Bullets.SMG_BULLET),
    ;

    private final int projectile;
    private final int timeReload;
    private final int ammoMax;
    private final int totalAmmo;
    private final float shootCoolDown;
    private final Bullets bullet;

    Weapons(int projectile,
            int timeReload,
            int ammoMax,
            int totalAmmo,
            float shootCoolDown,
            Bullets bullet) {
        this.projectile = projectile;
        this.timeReload = timeReload;
        this.ammoMax = ammoMax;
        this.shootCoolDown = shootCoolDown;
        this.bullet = bullet;
        this.totalAmmo = totalAmmo;
    }

    public int getAmmoMax() {
        return ammoMax;
    }

    public int getTotalAmmo() {
        return totalAmmo;
    }


    public int getProjectile() {
        return projectile;
    }

    public int getTimeReload() {
        return timeReload;
    }

    public Bullets getBullet() {
        return bullet;
    }

    public float getShootCoolDown() {
        return shootCoolDown;
    }

}
