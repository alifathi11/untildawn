package com.untildawn.Views;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.MenuControllers.LoginMenuController;
import com.untildawn.Main;
import com.untildawn.Models.GameAssetManager;

import java.util.function.Consumer;

public class LoginMenuView implements Screen, AppMenu {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;

    private final TextButton loginButton;
    private final TextButton signupButton;
    private final Label loginLabel;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextButton forgetPasswordButton;
    private Table table;

    private LoginMenuController controller;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.controller = controller;

        this.loginButton = new TextButton("LOGIN", skin);
        this.signupButton = new TextButton("I don't have an account.", skin, "textButtonNoBg");
        this.forgetPasswordButton = new TextButton("I've forgotten my password", skin, "textButtonNoBg");
        this.loginLabel = new Label("LOGIN MENU", skin);
        this.usernameField = new TextField("", skin);
        this.getUsernameField().setMessageText("Enter your username");
        this.passwordField = new TextField("", skin);
        this.passwordField.setMessageText("Enter your password");
        this.table = new Table();

        this.backgroundTexture = new Texture("images/background-image-4.png");
        backgroundImage = new Image(backgroundTexture);

        controller.setView(this);
        controller.handleLoginMenuButtons();
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
        table.add(loginLabel);
        table.row().pad(10, 5, 10, 5);
        table.add(usernameField).width(800).height(80);
        table.row().pad(10, 5, 10, 5);
        table.add(passwordField).width(800).height(80);
        table.row().pad(50, 5, 10, 5);
        table.add(loginButton).width(200).height(60);
        table.row().pad(20, 5, 10, 5);
        table.add(forgetPasswordButton).width(150).height(30);
        table.row().pad(20, 5, 10, 5);
        table.add(signupButton).width(150).height(30);

        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
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
    public void dispose() {
        stage.dispose();
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

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }

    public TextButton getSignupButton() {
        return signupButton;
    }
}
