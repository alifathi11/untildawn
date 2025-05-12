package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.GraphicsAudioSettingController;
import com.untildawn.Controllers.KeyboardPreferencesSettingController;
import com.untildawn.Main;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.MusicManager;

import java.util.function.Consumer;

public class KeyboardPreferencesSettingView implements Screen, AppMenu {
    private Stage stage;

    private Texture backgroundTexture;
    private Image backgroundImage;

    private final Label settingLabel;

    private Texture graphicsAndAudioSettingTexture;
    private Texture keyboardSettingTexture;

    private TextureRegionDrawable graphicsAndAudioDrawable;
    private TextureRegionDrawable keyboardDrawable;

    private ImageButton graphicAndAudioSettingButton;
    private ImageButton keyboardSettingButton;

    // TODO: change and save keyboard preferences



    private Table table;

    private KeyboardPreferencesSettingController controller;

    public KeyboardPreferencesSettingView(KeyboardPreferencesSettingController controller, Skin skin) {
        this.controller = controller;

        this.settingLabel = new Label("SETTING", skin);

        graphicsAndAudioSettingTexture = new Texture(Gdx.files.internal("images/graphic-audio-setting-icon.png"));
        keyboardSettingTexture = new Texture(Gdx.files.internal("images/keyboard-setting-icon.png"));

        graphicsAndAudioDrawable = new TextureRegionDrawable(new TextureRegion(graphicsAndAudioSettingTexture));
        keyboardDrawable = new TextureRegionDrawable(new TextureRegion(keyboardSettingTexture));

        graphicAndAudioSettingButton = new ImageButton(graphicsAndAudioDrawable);
        keyboardSettingButton = new ImageButton(keyboardDrawable);

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

        Container<ImageButton> graphicAudioSettingContainer = new Container<>(graphicAndAudioSettingButton);
        graphicAudioSettingContainer.size(60, 60);
        Container<ImageButton> keyboardSettingContainer = new Container<>(keyboardSettingButton);
        keyboardSettingContainer.size(60, 60);

        HorizontalGroup settingIcons = new HorizontalGroup();
        settingIcons.addActor(graphicAudioSettingContainer);
        settingIcons.addActor(keyboardSettingContainer);
        settingIcons.space(20);

        table.setFillParent(true);
        table.center();
        table.add(settingLabel);
        table.row().pad(40, 5, 10, 5);
        table.add(settingIcons).center().width(800).height(80);

        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        stage.addActor(table);
        controller.handleKeyBoardPreferencesSettingButton();
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

    public ImageButton getGraphicAndAudioSettingButton() {
        return graphicAndAudioSettingButton;
    }

    public ImageButton getKeyboardSettingButton() {
        return keyboardSettingButton;
    }
}
