package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.GameController;
import com.untildawn.Controllers.PauseMenuController;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.untildawn.Enums.Abilities;
import com.untildawn.Models.*;

import javax.swing.text.LabelView;

public class WinScreen {
    private final Stage stage;
    private Table table;
    private final Texture overlay;

    private Label usernameLabel;
    private Label gameTimeLabel;
    private Label killLabel;
    private Label scoreLabel;
    private TextButton continueButton;

    private Skin skin;

    private Game game;
    private Player player;
    private User user;

    public WinScreen(Game game) {
        this.stage = new Stage(new ScreenViewport());
        this.overlay = createOverlayTexture();

        table = new Table();
        table.center();
        table.setFillParent(true);
        table.setVisible(false);

        skin = GameAssetManager.getGameAssetManager().getSkin();

        this.continueButton = new TextButton("CONTINUE", skin);

        this.game = game;
        this.player = game.getPlayer();
        this.user = player.getUser();
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

        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        int minutes = (int)(game.getElapsedTime() / 60);
        int seconds = (int)(game.getElapsedTime() % 60);
        String timeStr = String.format("%02d:%02d", minutes, seconds);


        Label title = new Label("VICTORY!", skin, "title");
        title.setFontScale(2.5f);
        title.setAlignment(Align.center);

        usernameLabel = new Label("Player: " + user.getUsername(), skin);
        gameTimeLabel = new Label("Time: " + timeStr, skin);
        killLabel = new Label("Kills: " + player.getKill(), skin);
        scoreLabel = new Label("Score: " + player.getScore(), skin);

        float rowSpacing = 30f;

        table.add(title).padBottom(80f).colspan(2).center();
        table.row();

        table.add(usernameLabel).padBottom(rowSpacing).center();
        table.row();
        table.add(gameTimeLabel).padBottom(rowSpacing).center();
        table.row();
        table.add(killLabel).padBottom(rowSpacing).center();
        table.row();
        table.add(scoreLabel).padBottom(rowSpacing).center();
        table.row();
        table.add(continueButton).width(240).height(70).padTop(60f).center();

        table.getColor().a = 0f;
        table.addAction(Actions.fadeIn(1.0f));

        game.getGameController().handleEndGameMenuButtons();

    }


    public void render(Batch batch) {
        if (!game.isWon()) return;

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

    public TextButton getContinueButton() {
        return continueButton;
    }

    public Table getTable() {
        return table;
    }

}
