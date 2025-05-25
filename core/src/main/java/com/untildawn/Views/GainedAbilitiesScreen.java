package com.untildawn.Views;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Enums.Abilities;
import com.untildawn.Models.Game;
import com.untildawn.Models.GameAssetManager;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class GainedAbilitiesScreen {
    private Stage stage;
    private Table table;
    private Skin skin;
    private final Runnable onBack;
    private boolean visible = false;
    private Game game;

    public GainedAbilitiesScreen(Runnable onBackCallback, Game game) {
        this.onBack = onBackCallback;
        this.stage = new Stage(new ScreenViewport());
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.game = game;
        setupUI();
    }

    private void setupUI() {
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.top().pad(30);
        stage.addActor(table);

        Label title = new Label("GAINED ABILITIES", skin, "title");
        table.add(title).colspan(2).padBottom(30);
        table.row();
    }

    private void addRow(Abilities ability) {
        Label abilityNameLabel = new Label(ability.name(), skin);
        Texture abilityTexture = new Texture("abilities/" + ability.name().toLowerCase() + ".png");
        Image abilityImage = new Image(abilityTexture);
        Container<Image> abilityContainer = new Container<>(abilityImage);
        abilityContainer.size(100, 100);

        table.row().pad(20);
        table.add(abilityNameLabel).left().padRight(20);
        table.add(abilityContainer).left();
    }

    public void show() {
        visible = true;
        Gdx.input.setInputProcessor(this.getStage());

        ArrayList<Abilities> abilities = game.getPlayer().getAbilities();
        Set<Abilities> seen = new LinkedHashSet<>();
        abilities.removeIf(e -> !seen.add(e));

        for (Abilities ability : abilities) {
            addRow(ability);
        }

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

