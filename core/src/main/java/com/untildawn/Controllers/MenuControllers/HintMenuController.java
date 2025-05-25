package com.untildawn.Controllers.MenuControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.Menus;
import com.untildawn.Models.MenuManager;
import com.untildawn.Views.HintMenuView;

public class HintMenuController {
    private HintMenuView view;

    public void setView(HintMenuView view) {
        this.view = view;
    }

    public void handleHintMenuButtons() {
        view.getCheatCodesButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.showCheatScreen();
            }
        });
        view.getHeroStatsButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.showHeroStatsScreen();
            }
        });
        view.getAbilityStatsButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.showAbilityStatsScreen();
            }
        });
        view.getInputPreferencesButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.showInputPreferencesScreen();
            }
        });
        view.getBackButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.dispose();
                MenuManager.setScreen(Menus.MAIN_MENU);
            }
        });
    }
}
