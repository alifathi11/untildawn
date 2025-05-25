package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.MenuControllers.SignupMenuController;
import com.untildawn.Main;
import com.untildawn.Models.GameAssetManager;

import java.util.function.Consumer;

public class SignupMenuView implements Screen, AppMenu {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;

    private final TextButton signupButton;
    private final TextButton loginButton;
    private final TextButton playAsGuestButton;
    private final Label signupTitle;
    private final TextField usernameField;
    private final TextField emailField;
    private final TextField passwordField;
    private SelectBox<String> securityQuestionBox;
    private TextField userAnswer;
    private Table table;

    private SignupMenuController controller;

    public SignupMenuView(SignupMenuController controller, Skin skin) {
        this.controller = controller;

        this.signupButton = new TextButton("SIGN UP", skin);
        this.loginButton = new TextButton("I've already have an account.", skin, "textButtonNoBg");
        this.playAsGuestButton = new TextButton("Play as guest", skin, "textButtonNoBg");
        this.signupTitle = new Label("SIGN UP", skin);
        this.usernameField = new TextField("", skin);
        usernameField.setMessageText("Enter your username");
        this.emailField = new TextField("", skin);
        emailField.setMessageText("Enter your email address");
        this.passwordField = new TextField("", skin);
        passwordField.setMessageText("Enter your password");
        securityQuestionBox = new SelectBox<>(skin);
        Array<String> questions = new Array<>();
        questions.add("What was the name of your first pet?");
        questions.add("What is your mother's maiden name?");
        questions.add("What was the name of your elementary school?");
        questions.add("In what city were you born?");
        questions.add("What is your favorite food?");
        questions.add("What is the name of your best childhood friend?");
        questions.add("What is your favorite movie?");
        questions.add("What is your father's middle name?");
        questions.add("What was the make of your first car?");
        questions.add("What was the name of your first teacher?");

        securityQuestionBox.setItems(questions);
        securityQuestionBox.setPosition(100, 100);
        this.userAnswer = new TextField("", skin);
        userAnswer.setMessageText("Enter your answer here.");

        this.table = new Table();

        this.backgroundTexture = new Texture("images/background-image-4.png");
        backgroundImage = new Image(backgroundTexture);

        controller.setView(this);
        controller.handleSignupMenuButtons();
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
        table.add(signupTitle);
        table.row().pad(10, 5, 10, 5);
        table.add(usernameField).width(800).height(80);
        table.row().pad(10, 5, 10, 5);
        table.add(emailField).width(800).height(80);
        table.row().pad(10, 5, 10, 5);
        table.add(passwordField).width(800).height(80);
        table.row().pad(50, 5, 10, 5);
        table.row().pad(40, 5, 10, 5);
        table.add(securityQuestionBox).width(400).height(80);
        table.row().pad(20, 5, 10, 5);
        table.add(userAnswer).width(400).height(80);
        table.row().pad(60, 5, 10, 5);
        table.add(signupButton).width(200).height(60);
        table.row().pad(20, 5, 10, 5);
        table.add(loginButton).width(150).height(30);
        table.row().pad(20, 5, 10, 5);
        table.add(playAsGuestButton).width(150).height(30);

        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
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

    private void resetUI() {
        table.clear();
        stage.clear();

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

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getSignupButton() {
        return signupButton;
    }

    public TextField getUserAnswer() {
        return userAnswer;
    }

    public SelectBox<String> getSecurityQuestionBox() {
        return securityQuestionBox;
    }

    public TextButton getPlayAsGuestButton() {
        return playAsGuestButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

}
