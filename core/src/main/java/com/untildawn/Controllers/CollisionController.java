package com.untildawn.Controllers;

import com.untildawn.Enums.Monsters;
import com.untildawn.Enums.Projectiles;
import com.untildawn.Enums.Weapons;
import com.untildawn.Models.*;

import java.util.ArrayList;
import java.util.Random;

public class CollisionController {

    private World world;
    private Player player;
    private ProjectileController projectileController;
    private WeaponController weaponController;
    private MonsterController monsterController;

    public CollisionController(World world,
                               Player player,
                               ProjectileController projectileController,
                               WeaponController weaponController,
                               MonsterController monsterController) {
        this.world = world;
        this.player = player;
        this.projectileController = projectileController;
        this.weaponController = weaponController;
        this.monsterController = monsterController;
    }

    public void update() {
        playerCollidesWithMonsters();
        playerCollidesWithTrees();
        projectileCollidesWithMonsters();
        playerCollidesWithXP();
        playerCollidesWithAmmo();
        eyeMonsterProjectileCollidesWithPlayer();
    }

    public void playerCollidesWithMonsters() {
        ArrayList<Monster> monsters = world.getMonsters();
        ArrayList<Monster> monstersToDelete = new ArrayList<>();

        for (Monster monster : monsters) {
            CollisionRect monsterCollider = monster.getCollisionRect();
            CollisionRect playerCollider = player.getCollisionRect();
            if (playerCollider.collidesWith(monsterCollider)
            && !player.isInvincible()) {
                monstersToDelete.add(monster);
                player.decreaseHP();
                player.setInvincible(true);
                // TODO: sfx
            }
        }

        for (Monster monster : monstersToDelete) {
            if (monster.getMonsterType() == Monsters.EYE_MONSTER) {
                monsterController.decreaseEyeMonstersInWorld();
            }
            if (monster.getMonsterType() == Monsters.ELDER_MONSTER) {
                monsterController.decreaseElderMonstersInWorld();
                world.getProtectiveField().deactivate();
            }
            world.deleteMonster(monster);
        }
    }
    public void playerCollidesWithTrees() {
        ArrayList<Tree> trees = world.getTrees();
        for (Tree tree : trees) {
            CollisionRect treeCollider = tree.getCollisionRect();
            CollisionRect playerCollider = player.getCollisionRect();
            if (playerCollider.collidesWith(treeCollider)
            && !player.isInvincible()) {
                player.decreaseHP();
                player.setInvincible(true);
                // TODO: sfx
            }
        }
    }

    public void projectileCollidesWithMonsters() {
        ArrayList<Monster> monsters = world.getMonsters();
        ArrayList<Projectile> projectiles = projectileController.getProjectiles();

        ArrayList<Monster> monstersToDelete = new ArrayList<>();
        ArrayList<Projectile> projectilesToDelete = new ArrayList<>();

        for (Monster monster : monsters) {
            CollisionRect monsterCollider = monster.getCollisionRect();
            for (Projectile projectile : projectiles) {

                if (projectile.getProjectileType() == Projectiles.EYE_MONSTER_PROJECTILE) continue;

                CollisionRect projectileCollider = projectile.getCollisionRect();
                if (projectileCollider.collidesWith(monsterCollider)) {
                    projectilesToDelete.add(projectile);
                    monster.decreaseHP((int) projectile.getDamage());
                    if (monster.getHP() <= 0) {
                        monstersToDelete.add(monster);
                        // TODO: add XP
                        // TODO: add sfx
                    }
                }
            }
        }

        for (Monster monster : monstersToDelete) {

            if (monster.getMonsterType() == Monsters.EYE_MONSTER) {
                monsterController.decreaseEyeMonstersInWorld();
            }
            if (monster.getMonsterType() == Monsters.ELDER_MONSTER) {
                monsterController.decreaseElderMonstersInWorld();
                world.getProtectiveField().deactivate();
            }

            Position monsterPosition = monster.getPosition();
            XP xp = new XP(monster.getKillXP());
            world.deleteMonster(monster);

            xp.setPosition(monsterPosition);
            world.addXP(xp);

            Random random = new Random();

            if (random.nextInt() % 5 == 1) {

                Weapons weaponType = player.getCurrentWeapon().getWeaponType();
                int ammoAmount;

                switch (weaponType) {

                    case SHOTGUN:
                        ammoAmount = 16;
                        break;
                    case SMG:
                        ammoAmount = 50;
                        break;
                    case REVOLVER:
                        ammoAmount = 20;
                        break;
                    default:
                        return;
                }

                Ammo ammo = new Ammo(ammoAmount, weaponType);
                ammo.setPosition(new Position(monster.getPosition().getX() - 1, monster.getPosition().getY()));
                world.addAmmo(ammo);
            }
        }

        for (Projectile projectile : projectilesToDelete) projectileController.deleteProjectile(projectile);
    }

    public void eyeMonsterProjectileCollidesWithPlayer() {
        ArrayList<Projectile> projectiles = projectileController.getProjectiles();
        ArrayList<Projectile> projectilesToDelete = new ArrayList<>();

        for (Projectile projectile : projectiles) {

            if (projectile.getProjectileType() != Projectiles.EYE_MONSTER_PROJECTILE) continue;

            CollisionRect projectileCollider = projectile.getCollisionRect();
            CollisionRect playerCollider = player.getCollisionRect();

            if (projectileCollider.collidesWith(playerCollider)
            && !player.isInvincible()) {
                player.decreaseHP();
                player.setInvincible(true);
                projectilesToDelete.add(projectile);
                // TODO: add sfx
            }
        }

        for (Projectile projectile : projectilesToDelete) projectileController.deleteProjectile(projectile);
    }

    public void playerCollidesWithXP() {
        ArrayList<XP> xpsToDelete = new ArrayList<>();
        for (XP xp : world.getXps()) {
            CollisionRect xpCollider = xp.getCollisionRect();
            CollisionRect playerCollider = player.getCollisionRect();

            if (playerCollider.collidesWith(xpCollider)) {
                player.increaseXP(xp.getXp());
                xpsToDelete.add(xp);
            }
        }

        for (XP xp : xpsToDelete) {
            world.deleteXP(xp);
        }
    }

    public void playerCollidesWithAmmo() {
        ArrayList<Ammo> ammoToDelete = new ArrayList<>();
        for (Ammo ammo : world.getAmmo()) {
            CollisionRect ammoCollider = ammo.getCollisionRect();
            CollisionRect playerCollider = player.getCollisionRect();

            if (playerCollider.collidesWith(ammoCollider)) {
                Weapon weapon = weaponController.getWeaponByType(ammo.getWeaponType());
                weapon.increaseAmmo(ammo.getAmmoAmount());
                ammoToDelete.add(ammo);
                // TODO: add sfx
            }
        }

        for (Ammo ammo : ammoToDelete) {
            world.deleteAmmo(ammo);
        }
    }


}
