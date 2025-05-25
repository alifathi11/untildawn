package com.untildawn.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Models.App;
import com.untildawn.Models.Game;
import com.untildawn.Models.SFXManager;
import com.untildawn.Views.GameView;
import com.untildawn.Views.PauseMenuView;

public class PauseMenuController {

    private String[] grayScaleOptions;
    private int grayScaleIndex;

    private Game game;

    private boolean isPaused = false;
    private PauseMenuView view;

    private GameView gameView;

    public PauseMenuController(GameView gameView) {

        game = App.getCurrentGame();

        grayScaleOptions = new String[]{
            "OFF",
            "ON"
        };

        grayScaleIndex = game.getGamePreferences().isGrayScaleOn() ? 1 : 0;

        this.gameView = gameView;
    }

    public void setView(PauseMenuView view) {
        this.view = view;
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void handlePauseMenuButtons() {
        if (view != null) {
            view.getResumeButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setPaused(false);
                    view.getTable().setVisible(false);
                }
            });
            view.getGiveUpButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setGaveUp(true);
                    SFXManager.play("dead_screen");
                    setPaused(false);
                    view.getTable().setVisible(false);
                }
            });

            view.getCheatCodesButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameView.showCheatScreen();
                }
            });

            view.getShowAbilitiesButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameView.showGainedAbilitiesScreen();
                }
            });
            view.getGrayScaleButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    grayScaleIndex = (grayScaleIndex + 1) % grayScaleOptions.length;
                    view.getGrayScaleButton().setText(grayScaleOptions[grayScaleIndex]);
                    game.getGamePreferences().toggleGrayScale();
                }
            });
            view.getSaveGameButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // TODO: save and exit
                }
            });
        }
    }
}
