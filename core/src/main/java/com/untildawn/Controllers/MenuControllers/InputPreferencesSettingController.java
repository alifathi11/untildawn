package com.untildawn.Controllers.MenuControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.Actions;
import com.untildawn.Models.App;
import com.untildawn.Models.InputBinding;
import com.untildawn.Models.InputPreferences;
import com.untildawn.Models.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Views.InputPreferencesSettingView;
import com.untildawn.Views.MainMenuView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputPreferencesSettingController {
    private InputPreferencesSettingView view;
    private ArrayList<TextButton> changeButtons;
    Map<TextButton, Actions> buttonActionMap = new HashMap<>();

    private Actions actionBeingEdited = null;

    private InputPreferences inputPreferences;

    public InputPreferencesSettingController() {
        inputPreferences = App.getCurrentUser().getInputPreferences();
    }

    public void setView(InputPreferencesSettingView view) {
        this.view = view;
    }

    public void handleInputPreferencesSettingButton() {

        changeButtons = view.getChangeButtons();
        buttonActionMap = view.getButtonActionMap();

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

            view.getSubmitButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.MAIN_MENU);
                }
            });


            for (TextButton button : changeButtons) {
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Actions action = buttonActionMap.get(button);
                        startListeningForInput(action, button);
                    }
                });
            }
        }
    }


    private void startListeningForInput(Actions action, TextButton buttonToUpdate) {
        actionBeingEdited = action;
        buttonToUpdate.setText("Press key...");

        InputProcessor oldProcessor = Gdx.input.getInputProcessor();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (inputPreferences.isInputInUse(InputBinding.InputType.KEYBOARD, keycode, action)) {
                    buttonToUpdate.setText("Already used!");
                    Gdx.input.setInputProcessor(oldProcessor);
                    return true;
                }

                inputPreferences.setInputBinding(action, InputBinding.InputType.KEYBOARD, keycode);
                buttonToUpdate.setText(Input.Keys.toString(keycode));
                Gdx.input.setInputProcessor(oldProcessor);
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (action != Actions.SHOOT) {
                    buttonToUpdate.setText("Mouse not allowed");
                    Gdx.input.setInputProcessor(oldProcessor);
                    return true;
                }

                if (inputPreferences.isInputInUse(InputBinding.InputType.MOUSE, button, action)) {
                    buttonToUpdate.setText("Already used!");
                    Gdx.input.setInputProcessor(oldProcessor);
                    return true;
                }

                inputPreferences.setInputBinding(action, InputBinding.InputType.MOUSE, button);
                String buttonName;
                switch (button) {
                    case Input.Buttons.LEFT: buttonName = "Left Mouse"; break;
                    case Input.Buttons.RIGHT: buttonName = "Right Mouse"; break;
                    case Input.Buttons.MIDDLE: buttonName = "Middle Mouse"; break;
                    default: buttonName = "Mouse " + button; break;
                }
                buttonToUpdate.setText(buttonName);
                Gdx.input.setInputProcessor(oldProcessor);
                return true;
            }
        });
    }


}
