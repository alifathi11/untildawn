package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.Abilities;
import com.untildawn.Enums.Menus;
import com.untildawn.Main;
import com.untildawn.Models.*;
import com.untildawn.Views.GameView;
import com.untildawn.Views.PauseMenuView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameController {
    private Game game;
    private GameView view;
    private PlayerController playerController;
    private WeaponController weaponController;
    private WorldController worldController;
    private ProjectileController projectileController;
    private MonsterController monsterController;
    private CollisionController collisionController;
    private PauseMenuController pauseMenuController;
    private AbilityController abilityController;
    private PauseMenuView pauseMenuView;

    public GameController() {
        game = App.getCurrentGame();
    }

    public void setView(GameView view) {
        this.view = view;
        setControllers();
    }

    public void setControllers() {
        Game game = App.getCurrentGame();
        GamePreferences gamePreferences = game.getGamePreferences();

        this.projectileController = new ProjectileController();

        this.pauseMenuController = new PauseMenuController(view);
        this.pauseMenuView = new PauseMenuView(pauseMenuController);

        this.weaponController = new WeaponController(gamePreferences.getWeapon());
        this.playerController = new PlayerController(App.getCurrentGame().getPlayer());
        this.worldController = new WorldController(this);
        this.monsterController = new MonsterController(this);

        this.collisionController = new CollisionController(this);

        this.abilityController = new AbilityController();


        this.weaponController.setControllers(this);
        this.playerController.setControllers(this);
        this.worldController.setControllers(this);
        this.monsterController.setControllers(this);

        game.setController(this);
    }

    public void updateGame(float deltaTime) {
        if (view != null) {

            game.increaseElapsedTime(Gdx.graphics.getDeltaTime());

            this.worldController.update(deltaTime);
            this.playerController.update(deltaTime);
            this.weaponController.update();
            this.projectileController.update();
            this.monsterController.update(deltaTime);
            this.collisionController.update();

            this.checkGameFinish();

            updateAbilities(deltaTime);
        }
    }


    public PlayerController getPlayerController() {
        return playerController;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public ProjectileController getProjectileController() {
        return projectileController;
    }

    public MonsterController getMonsterController() {
        return monsterController;
    }

    public GameView getView() {
        return view;
    }

    public PauseMenuController getPauseMenuController() {
        return pauseMenuController;
    }

    public PauseMenuView getPauseMenuView() {
        return pauseMenuView;
    }

    public AbilityController getAbilityController() {
        return abilityController;
    }

    private void updateAbilities(float deltaTime) {
        Weapon weapon = weaponController.getCurrentWeapon();
        Player player = playerController.getPlayer();

        if (weapon.isOnDamager()) {
            weapon.increaseDamagerAbilityTimer(deltaTime);
            if (weapon.getDamagerAbilityTimer() >= weapon.getDamagerAbilityTime()) {
                weapon.setDamager(false);
            }
        }

        if (player.isOnSpeedy()) {
            player.increaseSpeedyAbilityTimer(deltaTime);
            if (player.getSpeedyAbilityTimer() >= player.getSpeedyAbilityTime()) {
                player.setSpeedy(false);
                player.setSpeed(player.getHero().getSpeed());
                player.setRunSpeed(player.getHero().getRunSpeed());
            }
        }
    }

    private void checkGameFinish() {
        int HP = (int) playerController.getPlayer().getHP();
        float remainingTime = game.getGamePreferences().getGameTime().getTime() - game.getElapsedTime();

        if (HP <= 0) {
            SFXManager.play("dead_screen");
            game.setLost(true);
            updateGameProfileAfterGame();

        }

        else if (remainingTime <= 0) {
            SFXManager.play("win_screen");
            game.setWon(true);
            updateGameProfileAfterGame();
        }

        else if (game.isGaveUp()) {
            updateGameProfileAfterGame();
        }
    }

    private void updateGameProfileAfterGame() {
        Player player = playerController.getPlayer();
        User user = player.getUser();
        GameProfile gameProfile = user.getGameProfile();

        gameProfile.increaseScore(player.getScore());
        gameProfile.increaseKillCount(player.getKill());

        float timeAlive = game.getElapsedTime();
        if (timeAlive > gameProfile.getMaxTimeAlive()) {
            gameProfile.setMaxTimeAlive(timeAlive);
        }
    }

    public void handleEndGameMenuButtons() {
        view.getWinScreen().getContinueButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.setCurrentGame(null);
                MenuManager.setScreen(Menus.MAIN_MENU);
            }
        });
        view.getDeadScreen().getContinueButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.setCurrentGame(null);
                MenuManager.setScreen(Menus.MAIN_MENU);
            }
        });
    }

}

