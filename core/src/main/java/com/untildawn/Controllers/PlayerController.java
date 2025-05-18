package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.untildawn.Main;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.Player;

public class PlayerController {
    private Player player;
    private PlayerAnimations playerAnimations;

    public PlayerController(Player player) {
        this.player = player;
        this.playerAnimations = new PlayerAnimations(player);
    }

    public void update() {
        player.getPlayerSprite().draw(Main.getBatch());

        if (player.isPlayerIdle()) {
            playerAnimations.idleAnimation();
        } else if (player.isPlayerWalking()) {
            playerAnimations.walkAnimation();
        }

        handlePlayerInput();
    }

    public void handlePlayerInput() {

        boolean isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.getPosition().setY((int) (player.getPosition().getY() + player.getSpeed()));
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.getPosition().setY((int) (player.getPosition().getY() - player.getSpeed()));
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.getPosition().setX((int) (player.getPosition().getX() + player.getSpeed()));
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.getPosition().setX((int) (player.getPosition().getX() - player.getSpeed()));
            isMoving = true;
        }

        player.setPlayerWalking(isMoving);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
