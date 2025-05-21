package com.untildawn.Controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.untildawn.Enums.Monsters;
import com.untildawn.Enums.Projectiles;
import com.untildawn.Main;
import com.untildawn.Models.*;

import java.util.ArrayList;
import java.util.Random;

public class MonsterController {
    private float brainMonsterSpawnTimer = 0f;
    private float eyeMonsterSpawnTimer = 0f;

    /// elder monster state variables
    private float elderMonsterSpawnTimer = 0f;
    private float elderMoveTimer = 0f;
    private boolean elderShouldMove = false;
    private float elderMovedDistance = 0f;
    private final float elderMoveCooldown = 5f;
    private final float elderMoveSpeed = 100f;
    private final float elderMaxMoveDistance = 200f;
    private final Vector2 elderMoveDirection = new Vector2();
    private boolean hasElderSpawned = false;
    ///

    private int eyeMonstersInWorld = 0;
    private int elderMonsterInWorld = 0;

    private Player player;
    private ProjectileController projectileController;


    private float elapsedTime = 0f;
    private OrthographicCamera camera;

    Game game;
    private World world;

    public MonsterController(GameController gameController) {
        this.camera = gameController.getView().getCamera();
        this.world = gameController.getWorldController().getWorld();

        this.game = App.getCurrentGame();
        this.player = game.getPlayer();

        this.projectileController = gameController.getProjectileController();
    }


    public void update(float deltaTime) {
        elapsedTime += deltaTime;

        spawnTrees();
        updateMonster(deltaTime);


        for (Monster monster : world.getMonsters()) {
            monster.getSprite().draw(Main.getBatch());
        }
    }

    private void updateMonster(float deltaTime) {
        // Brain Monsters
        brainMonsterSpawnTimer += deltaTime;

        float i = elapsedTime;
        float brainMonsterSpawnRate = i / 30f;

        if (brainMonsterSpawnTimer >= 3f / brainMonsterSpawnRate) {
            spawnMonster(Monsters.BRAIN_MONSTER);
            brainMonsterSpawnTimer = 0;
        }

        updateBrainMonsters(deltaTime);

        // Eye Monsters
        eyeMonsterSpawnTimer += deltaTime;

        float t = game.getGamePreferences().getGameTime().getTime();

        if (i < t / 4f) return;

        float eyeMonsterSpawnRate = (4f * i - t + 30f) / 30f;

        if (eyeMonsterSpawnRate <= 0f) return;

        float spawnInterval = 10f / eyeMonsterSpawnRate;

        if (eyeMonsterSpawnTimer >= spawnInterval
            && eyeMonstersInWorld < 5) {
            spawnMonster(Monsters.EYE_MONSTER);
            eyeMonstersInWorld++;
            eyeMonsterSpawnTimer = 0f;
        }


        updateEyeMonsters(deltaTime);

        // Elder Monster
        elderMonsterSpawnTimer += deltaTime;

        if (i < t / 2f) return;

        if ((t >= 600
            && elderMonsterSpawnTimer >= t / 4
            && elderMonsterInWorld < 1)
            || (t < 600
            && !hasElderSpawned)) {

            spawnMonster(Monsters.ELDER_MONSTER);
            hasElderSpawned = true;

            world.getProtectiveField().activate();

            elderMoveTimer = 0f;
            elderShouldMove = false;
            elderMovedDistance = 0f;

            elderMonsterInWorld++;

            elderMonsterSpawnTimer = 0f;
        }

        updateElderMonster(deltaTime);

    }

    private void spawnMonster(Monsters monsterType) {
        Monster monster = new Monster(
            monsterType
        );

        float worldLeft = camera.position.x - camera.viewportWidth / 2f;
        float worldRight = camera.position.x + camera.viewportWidth / 2f;
        float worldBottom = camera.position.y - camera.viewportHeight / 2f;
        float worldTop = camera.position.y + camera.viewportHeight / 2f;

        float x = 0, y = 0;
        int edge = new Random().nextInt(4);

        switch (edge) {
            case 0:
                x = randomFloat(worldLeft, worldRight);
                y = worldTop;
                break;
            case 1:
                x = randomFloat(worldLeft, worldRight);
                y = worldBottom;
                break;
            case 2:
                x = worldLeft;
                y = randomFloat(worldBottom, worldTop);
                break;
            case 3:
                x = worldRight;
                y = randomFloat(worldBottom, worldTop);
                break;
        }

        if (monsterType == Monsters.EYE_MONSTER) {
            monster.setEyeMonsterBehavior(true);

            Vector2 playerPos = player.getPosition().toVector2();
            Vector2 spawnPos = new Vector2(x, y);
            Vector2 dir = spawnPos.cpy().sub(playerPos).nor();
            Vector2 hoverPoint = playerPos.cpy().add(dir.scl(500));

            monster.setHoverOffsetTarget(hoverPoint);
        }

        monster.setPosition(new Position(x, y));
        world.addMonster(monster);
    }

    private void updateBrainMonsters(float delta) {
        Player player = App.getCurrentGame().getPlayer();

        float px = player.getPosition().getX();
        float py = player.getPosition().getY();

        for (Monster monster : world.getMonsters()) {
            if (monster.getMonsterType() != Monsters.BRAIN_MONSTER) continue;

            float mx = monster.getPosition().getX();
            float my = monster.getPosition().getY();

            float dx = px - mx;
            float dy = py - my;

            float length = (float) Math.sqrt(dx * dx + dy * dy);
            if (length == 0) continue;

            float speed = 100f; // TODO: monster.getSpeed()
            float vx = (dx / length) * speed * delta;
            float vy = (dy / length) * speed * delta;

            mx += vx;
            my += vy;

            monster.setPosition(new Position(mx, my));
            monster.getSprite().setPosition(mx, my);

            monster.setTime(monster.getTime() + delta);

            Animation<Texture> animation = GameAssetManager.getGameAssetManager().getMonsterAnimation(Monsters.BRAIN_MONSTER);
            monster.getSprite().setRegion(animation.getKeyFrame(monster.getTime(), true));
        }
    }

    private void updateEyeMonsters(float deltaTime) {
        Vector2 playerPos = player.getPosition().toVector2();

        for (Monster monster : world.getMonsters()) {
            if (!monster.isEyeMonster()) continue;

            Vector2 currentPos = monster.getPosition().toVector2();

            Vector2 direction = currentPos.cpy().sub(playerPos).nor();
            Vector2 desiredHover = playerPos.cpy().add(direction.scl(500));
            monster.setHoverOffsetTarget(desiredHover);

            if (!monster.hasReachedHover()) {

                Vector2 toHover = monster.getHoverOffsetTarget().cpy().sub(currentPos);
                float distance = toHover.len();

                float moveSpeed = 100f; // TODO: monster.getSpeed()
                if (distance < moveSpeed * deltaTime) {

                    monster.setReachedHover(true);
                } else {
                    Vector2 move = toHover.nor().scl(moveSpeed * deltaTime);
                    Vector2 newPos = monster.getPosition().toVector2().add(move);
                    monster.setPosition(new Position(newPos.x, newPos.y));
                }
            } else {
                float hoverAmplitude = 5f;
                float hoverSpeed = 3f;

                float xOffset = (float) Math.sin(monster.getElapsedTime() * hoverSpeed) * hoverAmplitude;
                float yOffset = (float) Math.cos(monster.getElapsedTime() * hoverSpeed) * hoverAmplitude;

                Vector2 base = monster.getHoverOffsetTarget();
                monster.setPosition(new Position(base.x + xOffset, base.y + yOffset));
            }

            // handle animation
            monster.setTime(monster.getTime() + deltaTime);
            Animation<Texture> animation = GameAssetManager.getGameAssetManager().getMonsterAnimation(Monsters.EYE_MONSTER);
            monster.getSprite().setRegion(animation.getKeyFrame(monster.getTime(), true));

            // handle shooting
            monster.addElapsedTime(deltaTime);
            monster.addTimeSinceLastShot(deltaTime);

            if (monster.getTimeSinceLastShot() >= 3.0f) {
                shootAtPlayer(monster);
                monster.resetShotCooldown();
            }
        }
    }

    private void updateElderMonster(float delta) {
        Player player = App.getCurrentGame().getPlayer();

        float px = player.getPosition().getX();
        float py = player.getPosition().getY();

        for (Monster monster : world.getMonsters()) {
            if (monster.getMonsterType() != Monsters.ELDER_MONSTER) continue;

            float mx = monster.getPosition().getX();
            float my = monster.getPosition().getY();

            elderMoveTimer += delta;

            if (!elderShouldMove && elderMoveTimer >= elderMoveCooldown) {
                elderShouldMove = true;
                elderMoveTimer = 0f;
                elderMovedDistance = 0f;

                float dx = px - mx;
                float dy = py - my;

                float length = (float) Math.sqrt(dx * dx + dy * dy);
                if (length != 0) {
                    elderMoveDirection.set(dx / length, dy / length);
                } else {
                    elderMoveDirection.set(0, 0);
                }
            }

            if (elderShouldMove) {
                float step = elderMoveSpeed * delta;

                if (elderMovedDistance + step >= elderMaxMoveDistance) {
                    step = elderMaxMoveDistance - elderMovedDistance;
                    elderShouldMove = false;
                }

                float vx = elderMoveDirection.x * step;
                float vy = elderMoveDirection.y * step;

                mx += vx;
                my += vy;

                monster.setPosition(new Position(mx, my));
                monster.getSprite().setPosition(mx, my);

                elderMovedDistance += step;
            }

//            // Animate monster
//            monster.setTime(monster.getTime() + delta);
//            Animation<Texture> animation = GameAssetManager.getGameAssetManager().getMonsterAnimation(Monsters.ELDER_MONSTER);
//            monster.getSprite().setRegion(animation.getKeyFrame(monster.getTime(), true));
        }
    }



    private void spawnTrees() {
        for (Tree tree : world.getTrees()) {
            Position pos = tree.getPosition();
            Main.getBatch().draw(
                tree.getTreeSprite(),
                pos.getX(), pos.getY(),
                tree.getTreeSprite().getWidth(),
                tree.getTreeSprite().getHeight()
            );
        }

        handleTreeAnimations();
    }

    private void handleTreeAnimations() {
        Player player = App.getCurrentGame().getPlayer();
        ArrayList<Tree> trees = world.getTrees();

        EnemyAnimations enemyAnimations = new EnemyAnimations();

        float playerX = player.getPosition().getX();
        float playerY = player.getPosition().getY();

        float triggerDistance = 100f;

        for (Tree tree : trees) {
            float treeX = tree.getPosition().getX() + tree.getTreeSprite().getWidth() / 2f;
            float treeY = tree.getPosition().getY() + tree.getTreeSprite().getHeight() / 2f;

            float dx = playerX - treeX;
            float dy = playerY - treeY;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance < triggerDistance) {
                tree.setAnimating(true);
            } else {
                tree.setAnimating(false);
                tree.setTime(0);
            }
        }

        for (Tree tree : world.getTrees()) {
            enemyAnimations.treeAnimations(tree);
        }
    }

    private void shootAtPlayer(Monster monster) {
        Vector2 monsterPos = monster.getPosition().toVector2();
        Vector2 playerPos = player.getPosition().toVector2();

        Vector2 direction = playerPos.cpy().sub(monsterPos);

        if (direction.len() == 0) {
            direction.set(1, 0);
        } else {
            direction.nor();
        }

        Projectile projectile = new Projectile(
            new Vector2(monsterPos),
            new Vector2(direction),
            Projectiles.EYE_MONSTER_PROJECTILE
        );

        projectileController.addProjectile(projectile);
    }

    private float randomFloat(float min, float max) {
        return min + new Random().nextFloat() * (max - min);
    }

    public void decreaseEyeMonstersInWorld() {
        this.eyeMonstersInWorld--;
    }
    public void decreaseElderMonstersInWorld() {
        this.elderMonsterInWorld--;
    }
}
