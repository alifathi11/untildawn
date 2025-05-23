package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Enums.Actions;
import com.untildawn.Models.App;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.InputBinding;
import com.untildawn.Models.InputPreferences;

public class InputPreferencesScreen {
    private Stage stage;
    private Table table;
    private Skin skin;
    private final Runnable onBack;
    private boolean visible = false;

    private InputPreferences inputPreferences;

    public InputPreferencesScreen(Runnable onBackCallback) {
        this.onBack = onBackCallback;
        this.stage = new Stage(new ScreenViewport());
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.inputPreferences = App.getCurrentUser().getInputPreferences();
        setupUI();
    }

    private void setupUI() {
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.top().pad(30);
        stage.addActor(table);

        Label title = new Label("INPUT PREFERENCES", skin, "title");
        table.add(title).colspan(2).padBottom(30);
        table.row();

        addRow("MOVE UP", getReadableInputName(inputPreferences.getInputBinding(Actions.MOVE_UP)));
        addRow("MOVE DOWN", getReadableInputName(inputPreferences.getInputBinding(Actions.MOVE_DOWN)));
        addRow("MOVE LEFT", getReadableInputName(inputPreferences.getInputBinding(Actions.MOVE_LEFT)));
        addRow("MOVE RIGHT", getReadableInputName(inputPreferences.getInputBinding(Actions.MOVE_RIGHT)));
        addRow("SHOOT", getReadableInputName(inputPreferences.getInputBinding(Actions.SHOOT)));
        addRow("AUTO SHOOTING", getReadableInputName(inputPreferences.getInputBinding(Actions.AUTO_SHOOTING)));
        addRow("RELOAD", getReadableInputName(inputPreferences.getInputBinding(Actions.RELOAD)));
        addRow("DANCE", getReadableInputName(inputPreferences.getInputBinding(Actions.DANCE)));
        addRow("RUN", getReadableInputName(inputPreferences.getInputBinding(Actions.RUN)));
        addRow("WEAPON 1", getReadableInputName(inputPreferences.getInputBinding(Actions.WEAPON_1)));
        addRow("WEAPON 2", getReadableInputName(inputPreferences.getInputBinding(Actions.WEAPON_2)));
        addRow("WEAPON 3", getReadableInputName(inputPreferences.getInputBinding(Actions.WEAPON_3)));
        addRow("PAUSE", getReadableInputName(inputPreferences.getInputBinding(Actions.PAUSE)));
        addRow("OPEN CHEAT CONSOLE", getReadableInputName(inputPreferences.getInputBinding(Actions.OPEN_CHEAT_CONSOLE)));

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hide();
                onBack.run();
            }
        });

        table.row().padTop(60);
        table.add(backButton).colspan(2).align(Align.center);
    }

    private void addRow(String code, String input) {
        Label codeLabel = new Label(code, skin);
        Label descLabel = new Label(input, skin);
        table.row().pad(20);
        table.add(codeLabel).left().padRight(20);
        table.add(descLabel).left();
    }

    public void show() {
        visible = true;
        Gdx.input.setInputProcessor(this.getStage());
    }

    public void hide() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void render(float delta) {
        if (!visible) return;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    private String getReadableInputName(InputBinding binding) {
        if (binding.getType() == InputBinding.InputType.KEYBOARD) {
            return Input.Keys.toString(binding.getCode());
        } else if (binding.getType() == InputBinding.InputType.MOUSE) {
            switch (binding.getCode()) {
                case Input.Buttons.LEFT: return "Left Mouse Button";
                case Input.Buttons.RIGHT: return "Right Mouse Button";
                case Input.Buttons.MIDDLE: return "Middle Mouse Button";
                default: return "Mouse Button " + binding.getCode();
            }
        }
        return "Unknown";
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }


    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        stage.dispose();
    }
}

