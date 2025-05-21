package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.untildawn.Main;
import com.untildawn.Models.*;

import java.awt.*;
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
            world.getBackgroundSprite(),
            0f,
            0f,
            world.getBackgroundTexture().getWidth(),
            world.getBackgroundTexture().getHeight()
        );

        // spawn XP
        for (XP xp : world.getXps()) {
            xp.getXpSprite().draw(Main.getBatch());
        }

        // spawn Ammo
        for (Ammo ammo : world.getAmmo()) {
            ammo.getAmmoSprite().draw(Main.getBatch());
        }

    }


    public World getWorld() {
        return world;
    }
}
