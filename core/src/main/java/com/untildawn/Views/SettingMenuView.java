package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.ProfileMenuController;
import com.untildawn.Controllers.SettingMenuController;
import com.untildawn.Main;
import com.untildawn.Models.App;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.MusicManager;

import java.util.function.Consumer;

public class SettingMenuView implements Screen, AppMenu {
    private Stage stage;

    private Texture backgroundTexture;
    private Image backgroundImage;

    private final Label settingLabel;
    private TextButton changeMusicButton;
    private TextButton musicToggleButton;
    private Slider musicVolumnSlider;
    private final TextButton submitButton;
    // TODO: change keyboard controllers keys

    private Table table;

    private SettingMenuController controller;

    public SettingMenuView(SettingMenuController controller, Skin skin) {
        this.controller = controller;

        this.settingLabel = new Label("SETTING", skin);
        this.submitButton = new TextButton("SUBMIT", skin);
        this.changeMusicButton = new TextButton("", skin);
        this.musicToggleButton = new TextButton("ON", skin);
        this.musicVolumnSlider = new Slider(0f, 1f,0.1f, false, skin);

        this.table = new Table();

        this.backgroundTexture = new Texture("images/background-image-2.png");
        backgroundImage = new Image(backgroundTexture);

        controller.setView(this);
    }
    @Override
    public void show() {
        if (stage != null) {
            stage.clear();
            table.clear();
        }
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        String currentMusic = "track: " + MusicManager.getCurrentTrackIndex();
        String musicState = MusicManager.isMusicPlaying() ? "ON" : "OFF";

        changeMusicButton.setText(currentMusic);
        musicToggleButton.setText(musicState);

        table.setFillParent(true);
        table.center();
        table.add(settingLabel);
        table.row().pad(20, 5, 10, 5);
        table.add(changeMusicButton).width(800).height(80);
        table.row().pad(10, 5, 10, 5);
        table.add(musicToggleButton).width(800).height(80);
        table.row().pad(60, 5, 10, 5);
        table.add(musicVolumnSlider).width(800).height(80);
        table.row().pad(20, 5, 10 ,5);
        table.add(submitButton).width(200).height(60);

        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        stage.addActor(table);
        controller.handleSettingMenuButtons();
    }

    @Override
    public void render(float delta) {
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (stage != null) {
            stage.getViewport().update(width, height, true);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void showError(String error) {
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        Dialog errorDialog = new Dialog("Error", skin) {
            @Override
            protected void result(Object object) {
            }
        };

        errorDialog.text(error);

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.getStyle().fontColor = Color.RED;
        errorDialog.button(closeButton);

        errorDialog.show(stage);

        errorDialog.setSize(400, 200);
        errorDialog.setPosition(
            (stage.getWidth() - errorDialog.getWidth()) / 2,
            (stage.getHeight() - errorDialog.getHeight()) / 2
        );
    }
    public void showMessage(String message) {
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        Dialog messageDialog = new Dialog("Alert", skin) {
            @Override
            protected void result(Object object) {
            }
        };

        messageDialog.text(message);

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.getStyle().fontColor = Color.RED;
        messageDialog.button(closeButton);

        messageDialog.show(stage);

        messageDialog.setSize(400, 200);
        messageDialog.setPosition(
            (stage.getWidth() - messageDialog.getWidth()) / 2,
            (stage.getHeight() - messageDialog.getHeight()) / 2
        );
    }
    public void showMessageAndExecute(String message, Runnable onClose) {
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        Dialog messageDialog = new Dialog("Alert", skin) {
            @Override
            protected void result(Object object) {
                if (onClose != null) {
                    onClose.run();
                }
            }
        };

        messageDialog.text(message);
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.getStyle().fontColor = Color.RED;

        messageDialog.button(closeButton, true);
        messageDialog.show(stage);

        messageDialog.setSize(400, 200);
        messageDialog.setPosition(
            (stage.getWidth() - messageDialog.getWidth()) / 2,
            (stage.getHeight() - messageDialog.getHeight()) / 2
        );
    }

    public void showConfirmation(String message, final Consumer<Boolean> resultCallback) {
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        Dialog dialog = new Dialog("Confirm", skin) {
            @Override
            protected void result(Object object) {
                if (object instanceof Boolean) {
                    resultCallback.accept((Boolean) object);
                }
            }
        };

        dialog.text(message);

        dialog.button("Yes", true);
        dialog.button("No", false);

        dialog.show(stage);

        dialog.setSize(400, 200);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2,
            (stage.getHeight() - dialog.getHeight()) / 2
        );
    }

    public TextButton getSubmitButton() {
        return submitButton;
    }

    public Slider getMusicVolumnSlider() {
        return musicVolumnSlider;
    }

    public TextButton getChangeMusicButton() {
        return changeMusicButton;
    }

    public TextButton getMusicToggleButton() {
        return musicToggleButton;
    }
}
