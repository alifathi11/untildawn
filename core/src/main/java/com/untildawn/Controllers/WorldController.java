package com.untildawn.Controllers;

import com.badlogic.gdx.graphics.Texture;
import com.untildawn.Main;

public class WorldController {
    private PlayerController playerController;
    private Texture backgroundTexture;
    private float backgroundX = 0;
    private float backgroundY = 0;

    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture("game-background.png");
        this.playerController = playerController;
    }

    public void update() {
        backgroundX = playerController.getPlayer().getPosition().getX();
        backgroundY = playerController.getPlayer().getPosition().getY();
        Main.getBatch().draw(backgroundTexture, backgroundX, backgroundY);
    }
}
