package com.untildawn.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Views.MainMenuView;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
            this.view.getNewGameButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.NEW_GAME_MENU);
                }
            });
            this.view.getResumeSavedGameButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.RESUME_LAST_GAME_MENU);
                }
            });
            this.view.getPreGameMenuButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.PREGAME_MENU);
                }
            });
            this.view.getHintMenuButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.HINT_MENU);
                }
            });
            this.view.getProfileMenuButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.PROFILE_MENU);
                }
            });
            this.view.getSettingMenuButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.GRAPHICS_AUDIO_SETTING_MENU);
                }
            });
            this.view.getScoreBoardMenuButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.SCOREBOARD_MENU);
                }
            });
            this.view.getLogoutButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.LOGOUT_MENU);
                }
            });
        }
    }
}
