package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.MenuControllers.MainMenuController;
import com.untildawn.Main;
import com.untildawn.Models.App;
import com.untildawn.Models.GameAssetManager;

public class MainMenuView implements Screen, AppMenu {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;

    private final Texture scoreTexture;
    private final Image coinImage;
    private final Container<Image> coinContainer;

    private Label usernameLabel;
    private Label scoreLabel;

    private final TextButton resumeSavedGameButton;
    private final TextButton preGameMenuButton;
    private final TextButton scoreBoardMenuButton;
    private final TextButton settingMenuButton;
    private final TextButton profileMenuButton;
    private final TextButton hintMenuButton;
    private final TextButton logoutButton;
    private final Label mainMenuLabel;
    private Table table;

    private MainMenuController controller;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;

        this.scoreTexture = new Texture(Gdx.files.internal("images/coin.png"));
        this.coinImage = new Image(scoreTexture);
        coinImage.setScaling(Scaling.fit);
        coinContainer = new Container<>(coinImage);
        coinContainer.size(40, 40);
        coinContainer.align(Align.center);

        this.resumeSavedGameButton = new TextButton("LOAD SAVED GAME", skin);
        this.preGameMenuButton = new TextButton("PREGAME MENU", skin);
        this.scoreBoardMenuButton = new TextButton("SCOREBOARD", skin);
        this.hintMenuButton = new TextButton("HINT MENU", skin);
        this.settingMenuButton = new TextButton("SETTING", skin);
        this.profileMenuButton = new TextButton("PROFILE", skin);
        this.logoutButton = new TextButton("LOGOUT", skin);

        this.mainMenuLabel = new Label("MAIN MENU", skin);

        this.table = new Table();

        this.backgroundTexture = new Texture("images/background-image.png");
        backgroundImage = new Image(backgroundTexture);

        controller.setView(this);
    }
    @Override
    public void show() {
        if (stage != null) {
            stage.clear();
            table.clear();
        }
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        String usernameStr = App.getCurrentUser() != null ? App.getCurrentUser().getUsername() : "Unknown";
        String scoreStr = App.getCurrentUser() != null ? Integer.toString(App.getCurrentUser().getGameProfile().getScore()) : "0";
        usernameLabel = new Label(usernameStr, skin);
        scoreLabel = new Label(scoreStr, skin);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        HorizontalGroup scoreGroup = new HorizontalGroup();
        scoreGroup.addActor(scoreLabel);
        scoreGroup.addActor(coinContainer);
        scoreGroup.align(Align.center);
        scoreGroup.space(10);

        HorizontalGroup userInfoGroup = new HorizontalGroup();
        userInfoGroup.addActor(usernameLabel);
        userInfoGroup.addActor(scoreGroup);
        userInfoGroup.space(540);

        Container<HorizontalGroup> userInfoContainer = new Container<>(userInfoGroup);
        userInfoContainer.center();

        table.setFillParent(true);
        table.center();

        table.add(mainMenuLabel).center();
        if (!App.isIsPlayingAsGuest()) {
            table.row().pad(10, 5, 10, 5);
            table.add(userInfoContainer).width(580).height(50).center();
        }
        table.row().pad(20, 5, 10, 5);
        table.add(resumeSavedGameButton).width(600).height(100);
        table.row().pad(10, 5, 10, 5);
        table.add(preGameMenuButton).width(600).height(100);
        table.row().pad(10, 5, 10, 5);
        table.add(scoreBoardMenuButton).width(600).height(100);
        table.row().pad(10, 5, 10, 5);
        table.add(hintMenuButton).width(600).height(100);
        table.row().pad(10, 5, 10, 5);
        table.add(settingMenuButton).width(600).height(100);
        table.row().pad(10, 5, 10, 5);
        table.add(profileMenuButton).width(600).height(100);
        table.row().pad(10, 5, 10, 5);
        table.add(logoutButton).width(600).height(100);

        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        stage.addActor(table);
        controller.handleMainMenuButtons();
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

    @Override
    public void showError(String error) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessageAndExecute(String message, Runnable onClose) {

    }

    public TextButton getPreGameMenuButton() {
        return preGameMenuButton;
    }

    public TextButton getProfileMenuButton() {
        return profileMenuButton;
    }

    public TextButton getScoreBoardMenuButton() {
        return scoreBoardMenuButton;
    }

    public TextButton getSettingMenuButton() {
        return settingMenuButton;
    }

    public TextButton getHintMenuButton() {
        return hintMenuButton;
    }

    public TextButton getLogoutButton() {
        return logoutButton;
    }

    public TextButton getResumeSavedGameButton() {
        return resumeSavedGameButton;
    }
}
