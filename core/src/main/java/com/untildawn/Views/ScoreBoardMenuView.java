package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.GraphicsAudioSettingController;
import com.untildawn.Controllers.ScoreBoardMenuController;
import com.untildawn.Main;
import com.untildawn.Models.App;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.MusicManager;
import com.untildawn.Models.User;

import javax.swing.text.LabelView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ScoreBoardMenuView implements Screen, AppMenu {

    private Stage stage;

    private Texture backgroundTexture;
    private Image backgroundImage;

    private final Texture goldTexture;
    private final Image goldImage;
    private final Container<Image> goldContainer;

    private final Texture silverTexture;
    private final Image silverImage;
    private final Container<Image> silverContainer;

    private final Texture bronzeTexture;
    private final Image bronzeImage;
    private final Container<Image> bronzeContainer;

    private final Label scoreBoardLabel;
    private TextButton sortTypeButton;
    private final TextButton backButton;

    private List<User> usersList;

    private Table table;

    private ScoreBoardMenuController controller;

    public ScoreBoardMenuView(ScoreBoardMenuController controller, Skin skin) {

        this.controller = controller;

        this.goldTexture = new Texture(Gdx.files.internal("images/gold-medal-icon.png"));
        this.goldImage = new Image(goldTexture);
        goldImage.setScaling(Scaling.fit);
        goldContainer = new Container<>(goldImage);
        goldContainer.size(40, 40);
        goldContainer.align(Align.center);

        this.silverTexture = new Texture(Gdx.files.internal("images/silver-medal-icon.png"));
        this.silverImage = new Image(silverTexture);
        silverImage.setScaling(Scaling.fit);
        silverContainer = new Container<>(silverImage);
        silverContainer.size(40, 40);
        silverContainer.align(Align.center);

        this.bronzeTexture = new Texture(Gdx.files.internal("images/bronze-medal-icon.png"));
        this.bronzeImage = new Image(bronzeTexture);
        bronzeImage.setScaling(Scaling.fit);
        bronzeContainer = new Container<>(bronzeImage);
        bronzeContainer.size(40, 40);
        bronzeContainer.align(Align.center);

        this.scoreBoardLabel = new Label("SCOREBOARD", skin);

        this.sortTypeButton = new TextButton("SORT BY SCORE", skin, "textButtonNoBg");
        this.backButton = new TextButton("BACK", skin);

        this.table = new Table();

        this.backgroundTexture = new Texture("images/background-image-2.png");
        backgroundImage = new Image(backgroundTexture);

        this.usersList = controller.getUsersBySelectedOrder("score");

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

        Skin skin = GameAssetManager.getGameAssetManager().getSkin();

        table.setFillParent(true);
        table.center();
        table.add(scoreBoardLabel);

        int usersShowLimit = 10;

        int counter = 1;
        for (User user : this.usersList.subList(0, Math.min(this.usersList.size(), usersShowLimit))) {
            HorizontalGroup row = getHorizontalGroup(user, counter++, user == App.getCurrentUser(), skin);

            table.row().pad(10, 5, 10, 5);
            table.add(row).width(1000).height(100).center();
        }

        table.row().pad(40, 10, 5, 10);
        table.add(sortTypeButton).width(200).height(60);
        table.row().pad(10, 10, 5, 10);
        table.add(backButton).width(200).height(60);

        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        stage.addActor(table);
        controller.handleScoreBoardMenuButtons();
    }

    private HorizontalGroup getHorizontalGroup(User user, int counter, boolean isCurrentUser, Skin skin) {
        HorizontalGroup row = new HorizontalGroup();

        row.space(150);

        Label username = new Label(user.getUsername(), skin);
        Label score = new Label(Integer.toString(user.getGameProfile().getScore()), skin);
        Label killCount = new Label(Integer.toString(user.getGameProfile().getKillCount()), skin);
        Label maxTimeAlive = new Label(Integer.toString(user.getGameProfile().getMaxTimeAlive()), skin);


        if (isCurrentUser) {
            username.setColor(Color.RED);
            score.setColor(Color.RED);
            killCount.setColor(Color.RED);
            maxTimeAlive.setColor(Color.RED);
        } else {
            username.setColor(Color.WHITE);
            score.setColor(Color.WHITE);
            killCount.setColor(Color.WHITE);
            maxTimeAlive.setColor(Color.WHITE);
        }

        row.addActor(username);
        row.addActor(score);
        row.addActor(killCount);
        row.addActor(maxTimeAlive);

        if (counter == 1) {
            row.addActor(goldContainer);
        } else if (counter == 2) {
            row.addActor(silverContainer);
        } else if (counter == 3) {
            row.addActor(bronzeContainer);
        }

        row.center();

        row.invalidate();

        return row;
    }

    public void update() {
        this.show();
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

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getSortTypeButton() {
        return sortTypeButton;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }
}
