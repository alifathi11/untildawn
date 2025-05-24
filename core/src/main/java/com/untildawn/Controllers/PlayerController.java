package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.untildawn.Enums.Actions;
import com.untildawn.Main;
import com.untildawn.Models.*;

public class PlayerController {
    private Player player;
    private InputPreferences playerInputPreferences;
    private PlayerAnimations playerAnimations;

    private WeaponController weaponController;
    private GameController gameController;
    private PauseMenuController pauseController;

    private Texture glowTexture;

    public PlayerController(Player player) {
        this.player = player;
        this.playerInputPreferences = player.getUser().getInputPreferences();
        this.playerAnimations = new PlayerAnimations(player);
        this.glowTexture = new Texture("images/glow_circle.png");
    }

    public void setControllers(GameController gameController) {
        this.weaponController = gameController.getWeaponController();
        this.gameController = gameController;
        this.pauseController = gameController.getPauseMenuController();
    }

    public void update(float deltaTime) {

        drawGlow();

        if (player.isInvincible()) {
            if (((int)(player.getInvincibleTime() * 10)) % 2 == 0) {
                player.getPlayerSprite().setAlpha(0.3f);
            } else {
                player.getPlayerSprite().setAlpha(1f);
            }
        } else {
            player.getPlayerSprite().setAlpha(1f);
        }

        player.getPlayerSprite().setCenter(
            player.getPosition().getX(),
            player.getPosition().getY()
        );

        if (player.isInvincible()) {
            player.increaseInvincibleTime(deltaTime);
            if (player.getInvincibleTime() >= player.getInvincibilityDuration()) {
                player.setInvincible(false);
                player.setInvincibleTime(0f);
            }
        }

        player.getPlayerSprite().draw(Main.getBatch());

        player.getCollisionRect().move(player.getPosition().getX(), player.getPosition().getY());
        if (!player.isOnGhostMode()) {
            if (player.isPlayerIdle()) {
                playerAnimations.idleAnimation();
            } else if (player.isPlayerRunning()) {
                playerAnimations.runAnimation();
            } else if (player.isPlayerWalking()) {
                playerAnimations.walkAnimation();
            }
        }

        handlePlayerInput();
    }

    public void handlePlayerInput() {

        boolean isPlayerMoving = false;
        boolean isPlayerIdle = false;
        boolean isPlayerRunning = false;

        InputBinding shootBinding = playerInputPreferences.getInputBinding(Actions.SHOOT);

        if (!weaponController.getCurrentWeapon().isReloading()) {
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

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.AUTO_SHOOTING).getCode())) {
            if (!weaponController.getCurrentWeapon().isReloading()) {
                weaponController.autoShooting();
            }
        }


        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.MOVE_UP).getCode())
            && canMoveTo(player.getPosition().getX(), player.getPosition().getY() + player.getSpeed())) {
            player.getPosition().setY((int) (player.getPosition().getY() + player.getSpeed()));
            isPlayerMoving = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.MOVE_DOWN).getCode())
            && canMoveTo(player.getPosition().getX(), player.getPosition().getY() - player.getSpeed())) {
            player.getPosition().setY((int) (player.getPosition().getY() - player.getSpeed()));
            isPlayerMoving = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.MOVE_RIGHT).getCode())
        && canMoveTo(player.getPosition().getX() + player.getSpeed(), player.getPosition().getY())) {
            player.getPosition().setX((int) (player.getPosition().getX() + player.getSpeed()));
            isPlayerMoving = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.MOVE_LEFT).getCode())
        && canMoveTo(player.getPosition().getX() - player.getSpeed(), player.getPosition().getY())) {
            player.getPosition().setX((int) (player.getPosition().getX() - player.getSpeed()));
            isPlayerMoving = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.DANCE).getCode())) {
            // TODO: can be changed
            isPlayerIdle = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.RUN).getCode())) {
            isPlayerRunning = true;
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.RELOAD).getCode())) {
            player.getCurrentWeapon().reload();
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.WEAPON_1).getCode())) {
            weaponController.changeWeapon(1);
        }
        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.WEAPON_2).getCode())) {
            weaponController.changeWeapon(2);
        }
        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.WEAPON_3).getCode())) {
            weaponController.changeWeapon(3);
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.PAUSE).getCode())) {
            pauseController.togglePause();
        }

        if (Gdx.input.isKeyPressed(playerInputPreferences.getInputBinding(Actions.OPEN_CHEAT_CONSOLE).getCode())) {
            gameController.getView().getCheatConsoleController().toggle();
        }


        player.setPlayerWalking(isPlayerMoving);
        player.setPlayerIdle(isPlayerIdle);
        player.setPlayerRunning(isPlayerRunning);
    }

    public boolean canMoveTo(float newX, float newY) {
        World world = gameController.getWorldController().getWorld();
        CollisionRect playerRect = player.getCollisionRect();

        playerRect.move(newX, newY);

        for (Tree tree : world.getTrees()) {
            if (playerRect.collidesWith(tree.getCollisionRect())) {
                return false;
            }
        }

        for (Monster monster : world.getMonsters()) {
            if (playerRect.collidesWith(monster.getCollisionRect())) {
                return false;
            }
        }

        return true;
    }

    private void drawGlow() {
        float playerX = player.getPosition().getX();
        float playerY = player.getPosition().getY();

        float glowSize = 250f;
        Main.getBatch().draw(glowTexture,
            playerX - glowSize / 2,
            playerY - glowSize / 2,
            glowSize,
            glowSize);
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
