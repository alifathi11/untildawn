package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Models.GameAssetManager;

public class HeroStatsScreen {
    private Stage stage;
    private Table characterTable;
    private Skin skin;
    private final Runnable onBack;
    private boolean visible = false;

    public HeroStatsScreen(Runnable onBackCallback) {
        this.onBack = onBackCallback;
        this.stage = new Stage(new ScreenViewport());
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        setupUI();
    }

    private void setupUI() {
        characterTable = new Table();
        characterTable.setFillParent(true);
        characterTable.top().pad(30);
        stage.addActor(characterTable);

        Label title = new Label("Character Stats", skin, "title");
        characterTable.add(title).colspan(5).padBottom(30);
        characterTable.row();

        characterTable.add(new Label("Character Name", skin)).pad(100, 10, 100, 50);
        characterTable.add(new Label("Image", skin)).pad(100, 50, 100, 50);
        characterTable.add(new Label("HP", skin)).pad(100, 50, 100, 50);
        characterTable.add(new Label("Speed", skin)).pad(100, 50, 100, 50);
        characterTable.add(new Label("Notes", skin)).pad(100, 50, 100, 10);
        characterTable.row();

        addRow(characterTable, "SHANA", "characters/shana.png", 4, 4, "Starter character. Can reroll upgrades once per level.");
        addRow(characterTable, "DIAMOND", "characters/diamond.png", 5, 1, "Has the highest starting HP of all the characters.");
        addRow(characterTable, "SCARLET", "characters/scarlet.png", 3, 5, "On every 3rd shot, throws out a wave of fire that burns\n\nenemies for 3 damage per second.");
        addRow(characterTable, "LILITH", "characters/lilith.png", 5, 3, "Summons a spirit when enemies are killed. Spirits chase\n\ndown nearby enemies and deal 8 damage.");
        addRow(characterTable, "DASHER", "characters/dasher.png", 2, 10, "Every 10 seconds, temporarily transforms into a deer.\n\nWhile in deer form, she is invincible and deals 100\n\ndamage to enemies she runs over. Deer form's damage is\n\nincreased by Summon damage and Move Speed.");

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hide();
                onBack.run();
            }
        });

        characterTable.row().padTop(40);
        characterTable.add(backButton).colspan(5).center();
    }


    private void addRow(Table table,
                        String name,
                        String imagePath,
                        int hp,
                        float speed,
                        String notes) {

        table.add(new Label(name, skin)).pad(100, 10, 50, 50);

        Texture texture = new Texture(Gdx.files.internal(imagePath));
        Image image = new Image(new TextureRegionDrawable(new TextureRegion(texture)));
        Container<Image> imageContainer = new Container<>(image);
        imageContainer.size(150, 150);
        imageContainer.align(Align.center);
        table.add(imageContainer).pad(10);

        table.add(new Label(String.valueOf(hp), skin)).pad(100, 50, 50, 50);
        table.add(new Label(String.valueOf((int) speed), skin)).pad(100, 50, 50, 50);
        table.add(new Label(notes, skin)).pad(100, 50, 50, 50).align(Align.left);

        table.row();
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

