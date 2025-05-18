package com.untildawn.Controllers;

import com.untildawn.Models.*;
import com.untildawn.Views.GameView;

public class GameController {
    private Game game;
    private GameView view;
    private PlayerController playerController;
    private WeaponController weaponController;
    private WorldController worldController;
    private BulletController bulletController;

    public GameController() {
        setControllers();
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void setControllers() {
        this.playerController = new PlayerController(App.getCurrentGame().getPlayer());
        this.bulletController = new BulletController();
        this.weaponController = new WeaponController(bulletController, new Weapon());
        this.worldController = new WorldController(playerController);
    }

    public void updateGame() {
        if (view != null) {
            this.worldController.update();
            this.playerController.update();
            this.weaponController.update();
            this.bulletController.update();
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

    public BulletController getBulletController() {
        return bulletController;
    }
}

