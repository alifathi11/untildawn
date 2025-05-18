package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.untildawn.Main;
import com.untildawn.Models.World;

public class WorldController {
    private PlayerController playerController;
    private World world;

    public WorldController(PlayerController playerController) {
        this.playerController = playerController;
        this.world = new World(playerController.getPlayer().getPosition().getX(),
                               playerController.getPlayer().getPosition().getY());
    }

    public void update() {
        this.world.setBackgroundX(playerController.getPlayer().getPosition().getX());
        this.world.setBackgroundY(playerController.getPlayer().getPosition().getY());

        Main.getBatch().draw(
            world.getBackgroundTexture(),
            -world.getBackgroundX() + Gdx.graphics.getWidth() / 2f,
            -world.getBackgroundY() + Gdx.graphics.getHeight() / 2f
        );
    }
}
