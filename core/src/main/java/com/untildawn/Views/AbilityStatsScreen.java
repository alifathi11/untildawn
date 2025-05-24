package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Models.GameAssetManager;

public class AbilityStatsScreen {
    private Stage stage;
    private Table table;
    private Skin skin;
    private final Runnable onBack;
    private boolean visible = false;

    public AbilityStatsScreen(Runnable onBackCallback) {
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

        Label title = new Label("ABILITY STATS", skin, "title");
        table.add(title).colspan(2).padBottom(30);
        table.row();

        addRow("VITALITY", "Permanently increase your maximum HP by 1.");
        addRow("DAMAGE", "Boost weapon damage by 25% for the next 10 seconds.");
        addRow("PROCREASE", "Add an extra projectile to your weapon's attack pattern.");
        addRow("AMMOCREASE", "Increase your weapon's maximum ammo capacity by 5");
        addRow("SPEEDY", "Double your movement speed for 10 seconds");

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

    private void addRow(String code, String description) {
        Label codeLabel = new Label(code, skin);
        Label descLabel = new Label(description, skin);
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

