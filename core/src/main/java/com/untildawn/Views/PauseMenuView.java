package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.GameController;
import com.untildawn.Controllers.PauseMenuController;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.untildawn.Enums.Abilities;
import com.untildawn.Models.App;
import com.untildawn.Models.Game;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.GamePreferences;

import javax.swing.text.LabelView;

public class PauseMenuView {
    private final Stage stage;
    private final PauseMenuController controller;
    private Table table;
    private final Texture overlay;

    private Label title;
    private TextButton resumeButton;
    private TextButton giveUpButton;
    private TextButton saveGameButton;
    private TextButton cheatCodesButton;
    private TextButton showAbilitiesButton;
    private Label grayScaleLabel;
    private TextButton grayScaleButton;

    private Skin skin;

    private Game game;

    public PauseMenuView(PauseMenuController controller) {
        this.controller = controller;
        this.stage = new Stage(new ScreenViewport());
        this.overlay = createOverlayTexture();

        table = new Table();
        table.setFillParent(true);
        table.setVisible(false);

        skin = GameAssetManager.getGameAssetManager().getSkin();

        title = new Label("Game Paused", skin);
        resumeButton = new TextButton("Resume", skin);
        giveUpButton = new TextButton("GIVE UP", skin);
        saveGameButton = new TextButton("SAVE AND EXIT", skin);
        cheatCodesButton = new TextButton("CHEAT CODES", skin);
        showAbilitiesButton =  new TextButton("SHOW ABILITIES", skin);

        game = App.getCurrentGame();
        GamePreferences gamePreferences = game.getGamePreferences();

        grayScaleLabel = new Label("GRAY SCALE:", skin);

        grayScaleButton = new TextButton(gamePreferences.isGrayScaleOn() ? "ON" : "OFF", skin);

        controller.setView(this);
        controller.handlePauseMenuButtons();
    }

    private Texture createOverlayTexture() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0, 0, 0, 0.5f));
        pixmap.fill();
        Texture tex = new Texture(pixmap);
        pixmap.dispose();
        return tex;
    }

    public void show() {

        table = new Table();
        table.setFillParent(true);
        table.setVisible(false);

        table.add(title);
        table.row().pad(40, 5, 10, 5);
        table.add(resumeButton).width(600).height(60);
        table.row().pad(10, 5, 10, 5);
        table.add(saveGameButton).width(600).height(60);
        table.row().pad(10, 5, 10, 5);
        table.add(showAbilitiesButton).width(600).height(60);
        table.row().pad(10, 5, 10, 5);
        table.add(cheatCodesButton).width(600).height(60);
        table.row().pad(10, 5, 10 ,5);
        table.add(giveUpButton).width(600).height(60);
        table.row().pad(20, 5, 10 ,5);
        table.add(grayScaleLabel);
        table.row().pad(10, 5, 10 ,5);
        table.add(grayScaleButton).width(600).height(60);

        stage.addActor(table);

        table.setVisible(true);
    }

    public void render(Batch batch) {
        if (!controller.isPaused()) return;

        batch.draw(overlay, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }

    public void hide() {
        table.setVisible(false);
    }

    public TextButton getCheatCodesButton() {
        return cheatCodesButton;
    }

    public TextButton getGiveUpButton() {
        return giveUpButton;
    }

    public TextButton getGrayScaleButton() {
        return grayScaleButton;
    }

    public TextButton getResumeButton() {
        return resumeButton;
    }

    public TextButton getSaveGameButton() {
        return saveGameButton;
    }

    public TextButton getShowAbilitiesButton() {
        return showAbilitiesButton;
    }

    public Table getTable() {
        return table;
    }

    public void dispose() {
        stage.dispose();
    }
}
