package com.untildawn.Controllers;

import com.untildawn.Models.App;
import com.untildawn.Models.Player;
import com.untildawn.Models.Weapon;
import com.untildawn.Views.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WeaponController weaponController;
    private WorldController worldController;

    public void setView(GameView view) {
        this.view = view;
    }

    public void setControllers() {
        this.playerController = new PlayerController(App.getCurrentGame().getPlayer());
        this.weaponController = new WeaponController(new Weapon());
        this.worldController = new WorldController(playerController);
    }

    public void updateGame() {
        if (view != null) {
            this.worldController.update();
            this.playerController.update();
            this.weaponController.update();
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
}
