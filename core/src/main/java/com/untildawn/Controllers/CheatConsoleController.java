package com.untildawn.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Models.App;
import com.untildawn.Models.Game;
import com.untildawn.Models.Player;
import com.untildawn.Views.CheatConsoleView;

public class CheatConsoleController {
    private CheatConsoleView view;

    Game game = App.getCurrentGame();
    Player player = game.getPlayer();

    public CheatConsoleController(CheatConsoleView view) {
        this.view = view;

        view.setSubmitListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String code = view.getCheatCode().trim().toLowerCase();

                switch (code) {
                    case "increase time":
                        game.increaseElapsedTime(60);
                        break;
                    case "level up":
                        player.levelUP();
                        break;
                    case "add hp":
                        player.increaseHP();
                        break;
                    case "boss fight":
                        game.increaseElapsedTime((float) (game.getGamePreferences().getGameTime().getTime()) / 2);
                        break;
                    case "god mode on":
                        player.setOnGodMode();
                        break;
                    case "ghost mode on":
                        player.setGhostMode(true);
                        break;
                    case "ghost mode off":
                        player.setGhostMode(false);
                        break;
                }

                view.clear();
                view.toggle();
            }
        });
    }

    public void toggle() {
        view.toggle();
    }

    public void updateAndRender() {
        view.update();
        view.render();
    }
}
