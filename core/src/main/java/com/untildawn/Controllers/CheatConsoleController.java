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
                    case "decrease time":
                        game.increaseElapsedTime(60);
                        break;
//                    case "level up":
//                        player.levelUp();
//                        break;
//                    case "add hp":
//                        player.AddHP();
//                        break;
//                    case "boss fight":
//                        game.goToBossFight();
//                        break;
//                    case "god mode":
//                        player.godModeOn();
//                        break;
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
