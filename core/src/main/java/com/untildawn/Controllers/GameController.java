package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.untildawn.Models.*;
import com.untildawn.Views.GameView;

public class GameController {
    private Game game;
    private GameView view;
    private PlayerController playerController;
    private WeaponController weaponController;
    private WorldController worldController;
    private ProjectileController projectileController;
    private MonsterController monsterController;
    private CollisionController collisionController;

    public GameController() {
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
        this.playerController = new PlayerController(App.getCurrentGame().getPlayer(), weaponController, this);
        this.worldController = new WorldController(playerController, this);
        this.monsterController = new MonsterController(this);
        this.collisionController = new CollisionController(worldController.getWorld(), playerController.getPlayer(), projectileController, weaponController, monsterController);
    }

    public void updateGame() {
        if (view != null) {
            this.worldController.update();
            this.playerController.update(Gdx.graphics.getDeltaTime());
            this.weaponController.update();
            this.projectileController.update();
            this.monsterController.update(Gdx.graphics.getDeltaTime());
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
}

