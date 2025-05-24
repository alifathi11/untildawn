package com.untildawn.Controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
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
    private WorldController worldController;


    public CollisionController(GameController gameController) {
        this.world = gameController.getWorldController().getWorld();
        this.player = App.getCurrentGame().getPlayer();
        this.projectileController = gameController.getProjectileController();
        this.weaponController = gameController.getWeaponController();
        this.monsterController = gameController.getMonsterController();
        this.worldController = gameController.getWorldController();
    }

    public void update() {
        playerCollidesWithMonsters();
        playerCollidesWithTrees();
        projectileCollidesWithMonsters();
        playerCollidesWithXP();
        playerCollidesWithAmmo();
        playerCollidesWithHeart();
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
                SFXManager.play("monster_damage");
            }
        }

        for (Monster monster : monstersToDelete) {

            monster.setDead(true);

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
                SFXManager.play("tree_damage");
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
                    int damage = (int) projectile.getDamage();
                    if (weaponController.getCurrentWeapon().isOnDamager()) {
                        damage *= 2;
                    }
                    monster.decreaseHP(damage);
                    float dx = monster.getPosition().getX() - projectile.getPosition().x;
                    float dy = monster.getPosition().getY() - projectile.getPosition().y;
                    monster.knockBack(dx, dy);
                    if (monster.getHP() <= 0) {
                        monstersToDelete.add(monster);
                        // TODO: add sfx
                    }
                }
            }
        }

        for (Monster monster : monstersToDelete) {

            monster.setDead(true);
            player.increaseKill();

            if (monster.getMonsterType() == Monsters.BRAIN_MONSTER) {
                SFXManager.play("brain_death");
            }
            if (monster.getMonsterType() == Monsters.EYE_MONSTER) {
                SFXManager.play("eye_death");
                monsterController.decreaseEyeMonstersInWorld();
            }
            if (monster.getMonsterType() == Monsters.ELDER_MONSTER) {
                SFXManager.play("elder_death");
                monsterController.decreaseElderMonstersInWorld();
                world.getProtectiveField().deactivate();
                MusicManager.play();
                SFXManager.fadeOutAndStop("boss_fight", 3);
            }

            Position monsterPosition = monster.getPosition();

            Animation<Texture> deathAnim = GameAssetManager.getGameAssetManager().getMonsterDeathAnimation(monster.getMonsterType());
            DeathAnimation anim = new DeathAnimation(deathAnim, new Vector2(monsterPosition.getX(), monsterPosition.getY()));
            world.addDeathAnimation(anim);

            world.deleteMonster(monster);

            XP xp = new XP(monster.getKillXP());
            xp.setPosition(monsterPosition);
            world.addXP(xp);

            Random random = new Random();

            if (random.nextInt() % 5 == 1) {

                Weapons weaponType = Weapons.values()[random.nextInt(Weapons.values().length)];
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
                ammo.setPosition(new Position(monster.getPosition().getX() - 5, monster.getPosition().getY()));
                world.addAmmo(ammo);
            }

            if ((random.nextInt() % 3 == 1
                && monster.getMonsterType() == Monsters.EYE_MONSTER)
                || monster.getMonsterType() == Monsters.ELDER_MONSTER) {
                Heart heart = new Heart();
                heart.setPosition(new Position(monster.getPosition().getX(), monster.getPosition().getY() - 5));
                world.addHeart(heart);
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
                SFXManager.play("projectile_damage");
                worldController.addEffect(new TimedEffect(player.getPosition().getX() + 5, player.getPosition().getY(), 0.2f));
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
                SFXManager.play("xp_collect");
            }
        }

        for (XP xp : xpsToDelete) {
            world.deleteXP(xp);
        }
    }

    public void playerCollidesWithHeart() {
        ArrayList<Heart> heartsToDelete = new ArrayList<>();
        for (Heart heart : world.getHearts()) {
            CollisionRect heartCollider = heart.getCollisionRect();
            CollisionRect playerCollider = player.getCollisionRect();

            if (playerCollider.collidesWith(heartCollider)) {
                player.increaseHP();
                heartsToDelete.add(heart);
                SFXManager.play("heart_collect");
            }
        }

        for (Heart heart : heartsToDelete) {
            world.deleteHeart(heart);
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
                SFXManager.play("ammo_collect");
            }
        }

        for (Ammo ammo : ammoToDelete) {
            world.deleteAmmo(ammo);
        }
    }


}
