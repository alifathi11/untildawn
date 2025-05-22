package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.untildawn.Models.*;
import com.untildawn.Views.GameView;
import com.untildawn.Views.PauseMenuView;

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
        this.weaponController = new WeaponController(projectileController, gamePreferences.getWeapon());

        this.pauseMenuController = new PauseMenuController();
        this.pauseMenuView = new PauseMenuView(pauseMenuController);

        this.playerController = new PlayerController(App.getCurrentGame().getPlayer(), weaponController, this, pauseMenuController);
        this.worldController = new WorldController(playerController, this);
        this.monsterController = new MonsterController(this);
        this.collisionController = new CollisionController(worldController.getWorld(), playerController.getPlayer(), projectileController, weaponController, monsterController, worldController);
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
}

