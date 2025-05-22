package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Main;
import com.untildawn.Models.GameAssetManager;

public class CheatCodesScreen {
    private Stage stage;
    private Table table;
    private Skin skin;
    private final Runnable onBack;
    private boolean visible = false; // Track visibility

    public CheatCodesScreen(Runnable onBackCallback) {
        this.onBack = onBackCallback;
        this.stage = new Stage(new ScreenViewport());
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        setupUI();
    }

    private void setupUI() {
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.top().pad(30);
        stage.addActor(table);

        Label title = new Label("Cheat Codes", skin, "title");
        table.add(title).colspan(2).padBottom(30);
        table.row();

        addCheat("INCREASE TIME", "Increase game time by 1 minute.");
        addCheat("LEVEL UP", "Level up by 1.");
        addCheat("ADD HP", "Restore 1 unit of HP.");
        addCheat("BOSS FIGHT", "Trigger boss by setting time to half.");
        addCheat("GOD MODE ON", "Enable infinite health and ammo.");
        addCheat("GHOST MODE ON", "Disable collision & boost speed.");
        addCheat("GHOST MODE OFF", "Disable Ghost Mode.");

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

    private void addCheat(String code, String description) {
        Label codeLabel = new Label(code, skin);
        Label descLabel = new Label(description, skin);
        table.row().pad(20);
        table.add(codeLabel).left().padRight(20);
        table.add(descLabel).left();
    }

    public void show() {
        visible = true;
        Gdx.input.setInputProcessor(stage);
    }

    public void hide() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void render(float delta) {
        if (!visible) return;
        stage.act(delta);
        stage.draw();
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

