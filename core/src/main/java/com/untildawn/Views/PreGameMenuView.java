package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.MenuControllers.PreGameMenuController;
import com.untildawn.Main;
import com.untildawn.Models.GameAssetManager;

import java.util.function.Consumer;

public class PreGameMenuView implements Screen, AppMenu {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;

    private final Label preGameLabel;
    private final Label heroLabel;
    private final Label weaponLabel;
    private final Label timeLabel;
    private final Label autoReloadLabel;
    private final Label grayScaleLabel;

    private TextButton weaponChangeButton;
    private TextButton heroChangeButton;
    private TextButton gameTimeChangeButton;
    private TextButton autoReloadButton;
    private TextButton grayScaleButton;
    private final TextButton startGameButton;
    private final TextButton backButton;


    private Table table;

    private PreGameMenuController controller;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.controller = controller;

        this.preGameLabel = new Label("PREGAME MENU", skin);
        this.heroLabel = new Label("HERO:", skin);
        this.weaponLabel = new Label("WEAPON:", skin);
        this.timeLabel = new Label("GAME TIME:", skin);
        this.autoReloadLabel = new Label("AUTO RELOAD:", skin);
        this.grayScaleLabel = new Label("GRAY SCALE:", skin);

        this.gameTimeChangeButton = new TextButton("2 MINUTES", skin);
        this.heroChangeButton = new TextButton("SHANA", skin);
        this.weaponChangeButton = new TextButton("REVOLVER", skin);
        this.startGameButton = new TextButton("START NEW GAME", skin);
        this.autoReloadButton = new TextButton("OFF", skin);
        this.grayScaleButton = new TextButton("OFF", skin);
        this.backButton = new TextButton("BACK", skin);

        this.table = new Table();

        this.backgroundTexture = new Texture("images/background-image-5.png");
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

        table.setFillParent(true);
        table.center();
        table.add(preGameLabel);
        table.row().pad(40, 5, 10, 5);
        table.add(heroLabel).width(800).height(80);
        table.row().pad(10, 5, 10, 5);
        table.add(heroChangeButton).width(800).height(80);
        table.row().pad(20, 5, 10, 5);
        table.add(weaponLabel).width(800).height(80);
        table.row().pad(10, 5, 10, 5);
        table.add(weaponChangeButton).width(800).height(80);
        table.row().pad(20, 5, 10, 5);
        table.add(timeLabel).width(800).height(80);
        table.row().pad(10, 5, 10, 5);
        table.add(gameTimeChangeButton).width(800).height(80);
        table.row().pad(20, 5, 10, 5);
        table.add(autoReloadLabel).width(800).height(80);
        table.row().pad(10, 5, 10, 5);
        table.add(autoReloadButton).width(800).height(80);
        table.row().pad(20, 5, 10, 5);
        table.add(grayScaleLabel).width(800).height(80);
        table.row().pad(10, 5, 10, 5);
        table.add(grayScaleButton).width(800).height(80);
        table.row().pad(40, 5, 10, 5);
        table.add(startGameButton).width(400).height(60);
        table.row().pad(40, 5, 10, 5);
        table.add(backButton).width(400).height(60);

        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        stage.addActor(table);
        controller.handlePreGameMenuButtons();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK, true);
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

    public TextButton getGameTimeChangeButton() {
        return gameTimeChangeButton;
    }

    public TextButton getHeroChangeButton() {
        return heroChangeButton;
    }

    public TextButton getStartGameButton() {
        return startGameButton;
    }

    public TextButton getWeaponChangeButton() {
        return weaponChangeButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getAutoReloadButton() {
        return autoReloadButton;
    }

    public TextButton getGrayScaleButton() {
        return grayScaleButton;
    }
}
