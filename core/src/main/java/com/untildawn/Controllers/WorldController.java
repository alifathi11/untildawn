package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.untildawn.Main;
import com.untildawn.Models.*;

import java.util.ArrayList;


public class WorldController {
    private PlayerController playerController;
    private GameController gameController;
    private World world;
    private OrthographicCamera camera;

    public WorldController(PlayerController playerController, GameController gameController) {
        this.playerController = playerController;
        this.world = new World();

        this.gameController = gameController;

        this.camera = gameController.getView().getCamera();

        WorldDesigner worldDesigner = new WorldDesigner();
        worldDesigner.designWorld(world);
    }

    public void update() {

        float halfViewportWidth = camera.viewportWidth / 2;
        float halfViewportHeight = camera.viewportHeight / 2;

        float bgWidth = world.getBackgroundTexture().getWidth();
        float bgHeight = world.getBackgroundTexture().getHeight();

        float camX = playerController.getPlayer().getPosition().getX();
        float camY = playerController.getPlayer().getPosition().getY();

        camX = Math.max(halfViewportWidth, camX);
        camX = Math.min(bgWidth - halfViewportWidth, camX);

        camY = Math.max(halfViewportHeight, camY);
        camY = Math.min(bgHeight - halfViewportHeight, camY);

        camera.position.set(camX, camY, 0);
        camera.update();

        Main.getBatch().setProjectionMatrix(camera.combined);

        Main.getBatch().draw(
            world.getBackgroundTexture(),
            0, 0
        );

        for (Tree tree : world.getTrees()) {
            Position pos = tree.getPosition();
            Main.getBatch().draw(
                tree.getTreeSprite(),
                pos.getX(), pos.getY(),
                tree.getTreeSprite().getWidth(),
                tree.getTreeSprite().getHeight()
            );
        }

        handleAnimations();
    }


    private void handleAnimations() {
        Player player = playerController.getPlayer();
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
}
