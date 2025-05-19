package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.untildawn.Enums.Actions;
import com.untildawn.Main;
import com.untildawn.Models.InputBinding;
import com.untildawn.Models.InputPreferences;
import com.untildawn.Models.Player;

public class PlayerController {
    private Player player;
    private InputPreferences playerInputPreferences;
    private PlayerAnimations playerAnimations;
    private WeaponController weaponController;
    private GameController gameController;

    public PlayerController(Player player, WeaponController weaponController, GameController gameController) {
        this.player = player;
        this.playerInputPreferences = player.getInputPreferences();
        this.playerAnimations = new PlayerAnimations(player);
        this.weaponController = weaponController;
        this.gameController = gameController;
    }

    public void update() {
        player.getPlayerSprite().setCenter(
            player.getPosition().getX(),
            player.getPosition().getY()
        );

        player.getPlayerSprite().draw(Main.getBatch());

        player.getCollisionRect().move(player.getPosition().getX(), player.getPosition().getY());

        if (player.isPlayerIdle()) {
            playerAnimations.idleAnimation();
        } else if (player.isPlayerRunning()) {
            playerAnimations.runAnimation();
        } else if (player.isPlayerWalking()) {
            playerAnimations.walkAnimation();
        }

        handlePlayerInput();
    }

    public void handlePlayerInput() {

        boolean isPlayerMoving = false;
        boolean isPlayerIdle = false;
        boolean isPlayerRunning = false;

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.MOVE_UP).getCode())) {
            player.getPosition().setY((int) (player.getPosition().getY() + player.getSpeed()));
            isPlayerMoving = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.MOVE_DOWN).getCode())) {
            player.getPosition().setY((int) (player.getPosition().getY() - player.getSpeed()));
            isPlayerMoving = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.MOVE_RIGHT).getCode())) {
            player.getPosition().setX((int) (player.getPosition().getX() + player.getSpeed()));
            isPlayerMoving = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.MOVE_LEFT).getCode())) {
            player.getPosition().setX((int) (player.getPosition().getX() - player.getSpeed()));
            isPlayerMoving = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.DANCE).getCode())) {
            // TODO: can be changed
            isPlayerIdle = true;
        }

        InputBinding shootBinding = playerInputPreferences.getInputBinding(Actions.SHOOT);

        if (!weaponController.getWeapon().isReloading()) {
            Vector3 mouseScreenPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Vector3 mouseWorldPos3D = gameController.getView().getCamera().unproject(mouseScreenPos);
            Vector2 mouseWorldPos = new Vector2(mouseWorldPos3D.x, mouseWorldPos3D.y);
            Vector2 playerWorldPos = new Vector2(player.getPosition().getX(), player.getPosition().getY());

            if ((shootBinding.getType() == InputBinding.InputType.MOUSE)
                && Gdx.input.isButtonPressed(shootBinding.getCode())) {
                weaponController.handleWeaponShoot(playerWorldPos, mouseWorldPos);
            } else if ((shootBinding.getType() == InputBinding.InputType.KEYBOARD)
                && Gdx.input.isKeyPressed(shootBinding.getCode())) {
                weaponController.handleWeaponShoot(playerWorldPos, mouseWorldPos);
            }
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.RUN).getCode())) {
            isPlayerRunning = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.RELOAD).getCode())) {
            weaponController.getWeapon().reload();
        }

        player.setPlayerWalking(isPlayerMoving);
        player.setPlayerIdle(isPlayerIdle);
        player.setPlayerRunning(isPlayerRunning);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
