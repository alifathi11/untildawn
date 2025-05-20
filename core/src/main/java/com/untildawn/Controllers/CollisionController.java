package com.untildawn.Controllers;

import com.untildawn.Models.*;

import java.util.ArrayList;

public class CollisionController {

    private World world;
    private Player player;
    private BulletController bulletController;

    public CollisionController(World world, Player player, BulletController bulletController) {
        this.world = world;
        this.player = player;
        this.bulletController = bulletController;
    }

    public void update() {
        playerCollidesWithBrainMonsters();
        playerCollidesWithTrees();
        bulletCollidesWithMonsters();
        playerCollidesWithXP();
    }

    public void playerCollidesWithBrainMonsters() {
        ArrayList<BrainMonster> brainMonsters = world.getBrainMonsters();
        ArrayList<BrainMonster> monstersToDelete = new ArrayList<>();

        for (BrainMonster brainMonster : brainMonsters) {
            CollisionRect monsterCollider = brainMonster.getCollisionRect();
            CollisionRect playerCollider = player.getCollisionRect();
            if (playerCollider.collidesWith(monsterCollider)) {
                monstersToDelete.add(brainMonster);
                player.decreaseHP();
                // TODO: sfx
            }
        }

        for (BrainMonster brainMonster : monstersToDelete) world.deleteBrainMonster(brainMonster);
    }
    public void playerCollidesWithTrees() {
        ArrayList<Tree> trees = world.getTrees();
        for (Tree tree : trees) {
            CollisionRect treeCollider = tree.getCollisionRect();
            CollisionRect playerCollider = player.getCollisionRect();
            if (playerCollider.collidesWith(treeCollider)) {
                player.decreaseHP();
                // TODO: sfx
            }
        }
    }
    public void bulletCollidesWithMonsters() {
        ArrayList<Monster> monsters = world.getMonsters();
        ArrayList<Bullet> bullets = bulletController.getBullets();

        ArrayList<Monster> monstersToDelete = new ArrayList<>();
        ArrayList<Bullet> bulletsToDelete = new ArrayList<>();

        for (Monster monster : monsters) {
            CollisionRect monsterCollider = monster.getCollisionRect();
            for (Bullet bullet : bullets) {
                CollisionRect bulletCollider = bullet.getCollisionRect();
                if (bulletCollider.collidesWith(monsterCollider)) {
                    bulletsToDelete.add(bullet);
                    monster.decreaseHP(bullet.getDamage());
                    if (monster.getHP() <= 0) {
                        monstersToDelete.add(monster);
                        // TODO: add XP
                        // TODO: add sfx
                    }
                }
            }
        }

        for (Monster monster : monstersToDelete) {
            Position monsterPosition = monster.getPosition();
            XP xp = new XP(monster.getKillXP());
            world.deleteMonster(monster);

            xp.setPosition(monsterPosition);
            world.addXP(xp);
        }

        for (Bullet bullet : bulletsToDelete) bulletController.deleteBullet(bullet);
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

}
