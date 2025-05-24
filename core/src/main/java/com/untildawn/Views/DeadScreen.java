package com.untildawn.Views;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

public class DeadScreen {
    private Stage stage;
    private Table table;
    private final Texture overlay;


    private TextButton continueButton;

    private Skin skin;

    private Game game;
    private Player player;
    private User user;


    public DeadScreen(Game game) {
        stage = new Stage(new ScreenViewport());
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

        Label title = new Label("YOU DIED", skin, "title");
        title.setFontScale(2f);
        title.setAlignment(Align.center);

        Label usernameLabel = new Label("Player: " + user.getUsername(), skin);
        Label gameTimeLabel = new Label("Time Survived: " + timeStr, skin);
        Label killLabel = new Label("Kills: " + player.getKill(), skin);
        Label scoreLabel = new Label("Score: " + player.getScore(), skin);

        float rowSpacing = 30f;

        table.add(title).colspan(2).padBottom(80f).center();
        table.row();

        table.add(usernameLabel).padBottom(rowSpacing).center();
        table.row();
        table.add(gameTimeLabel).padBottom(rowSpacing).center();
        table.row();
        table.add(killLabel).padBottom(rowSpacing).center();
        table.row();
        table.add(scoreLabel).padBottom(rowSpacing).center();
        table.row();
        table.add(continueButton).width(220).height(70).padTop(60f).center();

        game.getGameController().handleEndGameMenuButtons();
    }


    public void render(Batch batch) {
        if (!game.isLost() && !game.isGaveUp()) return;

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
