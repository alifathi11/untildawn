package com.untildawn.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.Hero;
import com.untildawn.Enums.Time;
import com.untildawn.Enums.Weapon;
import com.untildawn.Models.*;
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

            view.getStartGameButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    view.showConfirmation("START NEW GAME WITH THIS PREFERENCES?", (result) -> {
                        if (result) {
                            Weapon weapon = Weapon.values()[weaponIndex];
                            Hero hero = Hero.values()[heroIndex];
                            Time time = Time.values()[timeIndex];

                            GameSetting gameSetting = new GameSetting(time, weapon, hero);

                            Game game = new Game(gameSetting, null);

                            User user = App.getCurrentUser();
                            Player player = new Player(user, 0, hero.getHP(), game);
                            game.setPlayer(player);

                            App.setCurrentGame(game);
                        }
                    });
                }
            });
        }
    }
}
