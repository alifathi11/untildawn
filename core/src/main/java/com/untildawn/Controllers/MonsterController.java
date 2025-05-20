package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.untildawn.Enums.Monsters;
import com.untildawn.Main;
import com.untildawn.Models.*;

import java.util.ArrayList;
import java.util.Random;

public class MonsterController {
    private float spawnTimer = 0f;
    private float elapsedTime = 0f;
    private OrthographicCamera camera;

    private World world;

    public MonsterController(GameController gameController) {
        this.camera = gameController.getView().getCamera();
        this.world = gameController.getWorldController().getWorld();
    }


    public void update(float deltaTime) {
        updateBrainMonster(deltaTime);
        spawnTrees();

        for (BrainMonster monster : world.getBrainMonsters()) {
            monster.getSprite().draw(Main.getBatch());
        }
    }

    private void updateBrainMonster(float deltaTime) {
        elapsedTime += deltaTime;
        spawnTimer += deltaTime;

        float i = elapsedTime;
        float spawnRate = i / 30f;

        if (spawnTimer >= 3f / spawnRate) {
            spawnBrainMonster();
            spawnTimer = 0;
        }

        updateBrainMonsters(deltaTime);
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

    private void spawnBrainMonster() {
        BrainMonster monster = new BrainMonster(
            Monsters.BRAIN_MONSTER.getHP(),
            Monsters.BRAIN_MONSTER.getSpeed(),
            Monsters.BRAIN_MONSTER.getKillXP()
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

        monster.setPosition(new Position(x, y));
        world.addBrainMonster(monster);
    }

    private void updateBrainMonsters(float delta) {
        Player player = App.getCurrentGame().getPlayer();

        float px = player.getPosition().getX();
        float py = player.getPosition().getY();

        for (BrainMonster monster : world.getBrainMonsters()) {
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

            Animation<Texture> animation = GameAssetManager.getGameAssetManager().getBrainMonsterAnimation();
            monster.getSprite().setRegion(animation.getKeyFrame(monster.getTime(), true));
        }
    }

    private float randomFloat(float min, float max) {
        return min + new Random().nextFloat() * (max - min);
    }

}
