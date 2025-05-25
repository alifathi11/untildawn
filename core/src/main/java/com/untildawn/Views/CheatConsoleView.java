package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Models.GameAssetManager;

public class CheatConsoleView {
    private Stage stage;
    private Skin skin;
    private Table table;
    private TextField cheatField;
    private TextButton submitButton;
    private boolean visible = false;


    public CheatConsoleView() {
        stage = new Stage(new ScreenViewport());
        skin = GameAssetManager.getGameAssetManager().getSkin();

        cheatField = new TextField("", skin);
        submitButton = new TextButton("SUBMIT", skin);

        table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(cheatField).width(500).pad(70);
        table.row().padTop(10);
        table.add(submitButton).width(200).height(60);

        stage.addActor(table);
        table.setVisible(false);
    }

    public void toggle() {
        visible = !visible;
        table.setVisible(visible);
        if (visible) Gdx.input.setInputProcessor(stage);
        else Gdx.input.setInputProcessor(null);
    }

    public void update() {
        if (visible) stage.act(Gdx.graphics.getDeltaTime());
    }

    public void render() {
        if (visible) stage.draw();
    }

    public void setSubmitListener(ClickListener listener) {
        submitButton.addListener(listener);
    }

    public String getCheatCode() {
        return cheatField.getText();
    }

    public void clear() {
        cheatField.setText("");
    }

    public boolean isVisible() {
        return visible;
    }

    public Stage getStage() {
        return stage;
    }

    public void dispose() {
        stage.dispose();
    }
}
