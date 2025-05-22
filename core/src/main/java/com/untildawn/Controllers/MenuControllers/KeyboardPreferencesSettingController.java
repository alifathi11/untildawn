package com.untildawn.Controllers.MenuControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Models.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Views.KeyboardPreferencesSettingView;

public class KeyboardPreferencesSettingController {
    private KeyboardPreferencesSettingView view;

    public void setView(KeyboardPreferencesSettingView view) {
        this.view = view;
    }

    public void handleKeyBoardPreferencesSettingButton() {
        if (view != null) {

            view.getKeyboardSettingButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.KEYBOARD_SETTING_MENU);
                }
            });

            view.getGraphicAndAudioSettingButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.GRAPHICS_AUDIO_SETTING_MENU);
                }
            });
        }
    }
}
