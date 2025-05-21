package com.untildawn.Controllers;

import com.untildawn.Enums.Projectiles;
import com.untildawn.Models.Projectile;

import java.util.ArrayList;

public class ProjectileController {
    private ArrayList<Projectile> projectiles;

    public ProjectileController() {
        this.projectiles = new ArrayList<>();
    }

    public void update() {
        for (Projectile projectile : projectiles) {
            projectile.update();
            projectile.draw();
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }
    public void deleteProjectile(Projectile projectile) {
        this.projectiles.remove(projectile);
    }
    public void addProjectile(Projectile projectile) {
        this.projectiles.add(projectile);
    }
}
