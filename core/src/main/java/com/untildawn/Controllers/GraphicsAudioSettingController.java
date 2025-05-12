package com.untildawn.Controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Models.MusicManager;
import com.untildawn.Views.GraphicsAudioSettingView;

public class GraphicsAudioSettingController {
    private GraphicsAudioSettingView view;

    public void setView(GraphicsAudioSettingView view) {
        this.view = view;
    }

    public void handleGraphicAudioSettingMenuButtons() {
        if (view != null) {
            view.getMusicVolumnSlider().addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    float newVolume = view.getMusicVolumnSlider().getValue();
                    MusicManager.setVolume(newVolume);
                }
            });

            view.getChangeMusicButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MusicManager.nextTrack();
                    String trackInfo = "track: " + MusicManager.getCurrentTrackIndex();
                    view.getChangeMusicButton().setText(trackInfo);
                }
            });

            view.getMusicToggleButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MusicManager.toggleMusic();
                    String trackState = MusicManager.isMusicPlaying() ? "ON" : "OFF";
                    view.getMusicToggleButton().setText(trackState);
                }
            });

            view.getSubmitButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.MAIN_MENU);
                }
            });

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
