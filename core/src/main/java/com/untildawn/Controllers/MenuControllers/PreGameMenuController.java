package com.untildawn.Controllers.MenuControllers;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Controllers.GameController;
import com.untildawn.Enums.*;
import com.untildawn.Main;
import com.untildawn.Models.*;
import com.untildawn.Views.GameView;
import com.untildawn.Views.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;
    private final String[] timeOptions;
    private final String[] weaponOptions;
    private final String[] heroOptions;
    private int timeIndex;
    private int weaponIndex;
    private int heroIndex;

    public PreGameMenuController() {

        timeOptions = new String[]{
            "2 MINUTES",
            "5 MINUTES",
            "10 MINUTES",
            "20 MINUTES"
        };

        weaponOptions = new String[]{
            "REVOLVER",
            "SHOTGUN",
            "SMGs DUAL"
        };

        heroOptions = new String[]{
            "SHANA",
            "DIAMOND",
            "SCARLET",
            "LILITH",
            "DASHER"
        };

        this.timeIndex = 0;
        this.heroIndex = 0;
        this.weaponIndex = 0;
    }


    public void setView(PreGameMenuView view) {
        this.view = view;
    }

    public void handlePreGameMenuButtons() {
        if (view != null) {

            view.getGameTimeChangeButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    timeIndex = (timeIndex + 1) % timeOptions.length;
                    view.getGameTimeChangeButton().setText(timeOptions[timeIndex]);
                }
            });

            view.getHeroChangeButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    heroIndex = (heroIndex + 1) % heroOptions.length;
                    view.getHeroChangeButton().setText(heroOptions[heroIndex]);
                }
            });

            view.getWeaponChangeButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    weaponIndex = (weaponIndex + 1) % weaponOptions.length;
                    view.getWeaponChangeButton().setText(weaponOptions[weaponIndex]);
                }
            });

            view.getBackButton().addListener(new ClickListener() {
               @Override
               public void clicked(InputEvent event, float x, float y) {
                   MenuManager.setScreen(Menus.MAIN_MENU);
               }
            });

            view.getStartGameButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    view.showConfirmation("START NEW GAME WITH\n    THESE PREFERENCES?", (result) -> {
                        if (result) {
                            Weapons weapon = Weapons.values()[weaponIndex];
                            Heros hero = Heros.values()[heroIndex];
                            Time time = Time.values()[timeIndex];

                            GameSetting gameSetting = new GameSetting(time, weapon, hero);

                            Game game = new Game(gameSetting, null);

                            User user = App.getCurrentUser();
                            Player player = new Player(user, 0, hero, game);
                            game.setPlayer(player);

                            App.setCurrentGame(game);

                            Main.getMain().getScreen().dispose();
                            Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
                        }
                    });
                }
            });
        }
    }
}
